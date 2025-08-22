package Main;

import DAO.Dao;
import Product.Comida;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main 
{
	private static String user = "postgres";
	private static String password = "2305";
	private static String url = "jdbc:postgresql://localhost:5432/Comidas";
	
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
	}
}
