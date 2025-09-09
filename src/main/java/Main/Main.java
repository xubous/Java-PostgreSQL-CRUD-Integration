package Main;

import DAO.Dao;
import Product.Comida;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import static spark.Spark.*;
import com.google.gson.Gson;

public class Main 
{
	private static String user = "postgres";
	private static String password = "2305";
	private static String url = "jdbc:postgresql://localhost:5432/Comidas";
	private static Gson gson = new Gson ( );
	
	public static String [ ] toString ( List < Comida > foods )
	{
		String [ ] strings = new String [ foods.size ( ) ];
		
		for ( int i = 0; i < foods.size ( ); i ++ )
		{
			Comida comida = foods.get ( i );
			
			strings [ i ] = String.format (
					"Id: %d -> Quantidade %d -> Valor %.2f",
					comida.getId ( ),
					comida.getQuantidade ( ),
					comida.getValor ( )
					);
		}
		
		return strings;
	}
	
	public static Comida createNewFood ( int id, int quantidade, double valor )
	{
		Comida comida = new Comida ( );
		
		comida.setId ( id );
		comida.setQuantidade ( quantidade );
		comida.setValor ( valor );
		
		return comida;
	}
	
	public static Connection createNewConnection ( )
	{
		Connection connection = null;
		
		try
		{
			connection = DriverManager.getConnection ( url, user, password );
			
		} catch ( SQLException e )
			{
				System.out.println ( e.getMessage ( ) );
			}
		
		if ( connection != null )
		{
			System.out.println ( "Connected" );
		}
		
		return connection;
		
	}
	
	public static void main ( String [ ] args ) throws SQLException
	{
		staticFiles.location ( "/public" );
		
		// Configuração CORS
		options ( "/*", ( request, response ) -> {
			
			String accessControlRequestHeaders = request.headers ( "Access-Control-Request-Headers" );
			if ( accessControlRequestHeaders != null )
			{
				response.header ( "Access-Control-Allow-Headers", accessControlRequestHeaders );
			}
			
			String accessControlRequestMethod = request.headers ( "Access-Control-Request-Method" );
			if ( accessControlRequestMethod != null )
			{
				response.header ( "Access-Control-Allow-Methods", accessControlRequestMethod );
			}
			
			return "OK";
		} );
		
		before ( ( request, response ) -> {
			response.header ( "Access-Control-Allow-Origin", "*" );
			response.header ( "Access-Control-Allow-Headers", "*" );
			response.header ( "Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS" );
			response.type ( "application/json" );
		} );
		
		// Rotas da API
		get ( "/comidas", ( req, res ) -> {
			try ( Connection connection = createNewConnection ( ) )
			{
				Dao dao = new Dao ( );
				List < Comida > allFoods = dao.showAllFoods ( connection );
				return gson.toJson ( allFoods );
			}
		} );
		
		post ( "/comidas", ( req, res ) -> {
			try ( Connection connection = createNewConnection ( ) )
			{
				Dao dao = new Dao ( );
				Comida comida = gson.fromJson ( req.body ( ), Comida.class );
				
				int result = dao.addNewFood ( comida, connection );
				if ( result > 0 )
				{
					res.status ( 201 );
					return gson.toJson ( new Response ( "Comida adicionada com sucesso!" ) );
				}
				else
				{
					res.status ( 400 );
					return gson.toJson ( new Response ( "Erro ao adicionar comida" ) );
				}
			}
		} );
		
		put ( "/comidas/:id", ( req, res ) -> {
			try ( Connection connection = createNewConnection ( ) )
			{
				Dao dao = new Dao ( );
				int id = Integer.parseInt ( req.params ( ":id" ) );
				Comida comida = gson.fromJson ( req.body ( ), Comida.class );
				
				int result = dao.updateFood ( id, comida.getQuantidade ( ), comida.getValor ( ), connection );
				if ( result > 0 )
				{
					return gson.toJson ( new Response ( "Comida atualizada com sucesso!" ) );
				}
				else
				{
					res.status ( 404 );
					return gson.toJson ( new Response ( "Comida não encontrada" ) );
				}
			}
		} );
		
		delete ( "/comidas/:id", ( req, res ) -> {
			try ( Connection connection = createNewConnection ( ) )
			{
				Dao dao = new Dao ( );
				int id = Integer.parseInt ( req.params ( ":id" ) );
				
				int result = dao.deleteFood ( id, connection );
				if ( result > 0 )
				{
					return gson.toJson ( new Response ( "Comida excluída com sucesso!" ) );
				}
				else
				{
					res.status ( 404 );
					return gson.toJson ( new Response ( "Comida não encontrada" ) );
				}
			}
		} );
		
		// Código original de teste
		Connection connection = createNewConnection ( );
		Dao dao = new Dao ( );
		Comida comida01 = createNewFood ( 2, 10, 24.56 );
		
		int returnAddNewFoodCheck = dao.addNewFood ( comida01, connection );
		
		System.out.println ( returnAddNewFoodCheck );
		
		int returnUpdateFood = dao.updateFood ( 2, 20, 44.55, connection );
		
		System.out.println ( returnUpdateFood );
		
		int returnDeleteFood = dao.deleteFood ( 2, connection );
		
		System.out.println ( returnDeleteFood );
		
		List < Comida > allFoods = new ArrayList < > ( );
		
		allFoods = dao.showAllFoods ( connection );
		
		String [ ] allFoodsToString = toString ( allFoods );
		
		for ( String i : allFoodsToString )
		{
			System.out.println ( i );
		}
		
		connection.close ( );
		
		System.out.println ( "Servidor Spark rodando em http://localhost:4567" );
	}
	
	// Classe interna para resposta
	static class Response
	{
		private String message;
		
		public Response ( String message )
		{
			this.message = message;
		}
		
		public String getMessage ( )
		{
			return message;
		}
		
		public void setMessage ( String message )
		{
			this.message = message;
		}
	}
}