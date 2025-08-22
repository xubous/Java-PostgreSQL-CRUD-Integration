package DAO;

import Product.Comida;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Dao 
{
	public int addNewFood ( Comida comida, Connection connection )
	{
		String string = "INSERT INTO Comida (id, quantidade, valor) VALUES (?, ?, ?)";
		
		try
		{
			PreparedStatement pptt = connection.prepareStatement ( string );
			pptt.setInt ( 1, comida.getId ( ) );
			pptt.setInt ( 2, comida.getQuantidade ( ) );
			pptt.setDouble ( 3, comida.getValor ( ) );
			
			pptt.executeUpdate ( );
			
			return 0;
		} catch ( SQLException e )
			{
				System.out.println ( e.getMessage ( ) );
			}
		
		
		return 1;
	}
	
	public int updateFood ( int id, int quantidade, double valor, Connection connection )
	{
		String string = "UPDATE Comida SET quantidade = ?, valor = ? WHERE id = ?";
		
		try
		{
			PreparedStatement pptt = connection.prepareStatement ( string );
			
			pptt.setInt ( 1, quantidade );
			pptt.setDouble ( 2, valor );
			pptt.setInt ( 3 , id );
			
			pptt.executeUpdate ( );
			
			return 0;
		} catch ( SQLException e )
			{
				System.out.println ( e.getMessage ( ) );
			}
		
		return 1;
	}
	
	public int deleteFood ( int id, Connection connection )
	{
		String string = "DELETE FROM Comida WHERE id = ?";
		
		try
		{
			PreparedStatement pptt = connection.prepareStatement ( string );
			
			pptt.setInt ( 1, id );
			pptt.executeUpdate ( );
			
			return 0;
		} catch ( SQLException e )
			{
				System.out.println ( e.getMessage ( ) );
			}
		
		return 1;
	}
	
	public List < Comida > showAllFoods ( Connection connection )
	{
		String string = "SELECT * FROM Comida";
		List < Comida > foods = new ArrayList < > ( );
		
		try
		{
			PreparedStatement pptt = connection.prepareStatement ( string );
			ResultSet rs = pptt.executeQuery ( );
			
			while ( rs.next ( ) )
			{
				Comida comida = new Comida ( );
				
				comida.setId ( rs.getInt ( "id" ) );
				comida.setQuantidade ( rs.getInt ( "quantidade" ) );
				comida.setValor ( rs.getDouble ( "valor" ) );
				
				foods.add ( comida );
			}
			
		} catch ( SQLException e )
			{
				System.out.println ( e.getMessage ( ) );
			}
	
		return foods;
	}
	
}
