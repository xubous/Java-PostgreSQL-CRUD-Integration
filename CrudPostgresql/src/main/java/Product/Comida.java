package Product;

public class Comida 
{
	private int id;
	private int quantidade;
	private double valor;
	
	public Comida ( )
	{
		this.id = - 1;
		this.quantidade = - 1;
		this.valor = 0.0;
	}
	
	public int getId ( ) 
	{
		return this.id;
	}
	
	public int getQuantidade ( ) 
	{
		return this.quantidade;
	}
	
	public double getValor ( ) 
	{
		return this.valor;
	}
	
	public void setId ( int id )
	{
		this.id = id;
	}
	
	public void setQuantidade ( int quantidade )
	{
		this.quantidade = quantidade;
	}
	
	public void setValor ( double valor )
	{
		this.valor = valor;
	}
}
