package it.unisalento.taco.model;

import java.util.ArrayList;
import java.util.List;

public class Prodotto {
	
	private final String nome;
	private final double prezzo;
	private final Produttore produttore;
	private String descrizione;
	private Categoria categoria;
	private List<Fornitore> listaFornitori = new ArrayList<Fornitore>();
	
	public String getNome() {
		return nome;
	}
	
	public String getDescrizione() {
		return descrizione;
	}
	
	public Categoria getCategoria() {
		return categoria;
	}
	
	public double getPrezzo() {
		return prezzo;
	}
	
	public Produttore getProduttore() {
		return produttore;
	}
	
	public List<Fornitore> getListaFornitori(){
		return listaFornitori;
	}
	
	public String getListaFornitoriAsString(){
		StringBuilder stringFornitori = new StringBuilder();
		for(Fornitore val : listaFornitori)
			stringFornitori.append(val.toString() + " ");
		return stringFornitori.toString();
		
	}
	
	public void addFornitore(Fornitore fornitore){
		if (listaFornitori.contains(Fornitore.FORNITORE_0))
			listaFornitori.remove(Fornitore.FORNITORE_0);
		listaFornitori.add(fornitore);
	}
	
	public static class Builder{
		private final String nome;
		private final double prezzo;
		private final Produttore produttore;
		private String descrizione;
		private Categoria categoria;
		private List<Fornitore> listaFornitori = new ArrayList<Fornitore>();
		
		public Builder(String nome, double prezzo, Produttore produttore){
			this.nome = nome;
			this.prezzo = prezzo;
			this.produttore = produttore;
			this.descrizione = "Nessuna descrizione";
			this.categoria = Categoria.CATEGORIA_0;
			this.listaFornitori.add(Fornitore.FORNITORE_0);
		}
		
		public Builder descrizione(String val){
			descrizione = val;
			return this;
		}
		
		public Builder categoria(Categoria val){
			categoria = val;
			return this;
		}
		
		public Builder listaFornitori(Fornitore... args){
			listaFornitori.remove(Fornitore.FORNITORE_0);
			for(Fornitore val : args)
				listaFornitori.add(val);
			return this;
		}
		
		public Prodotto build(){
			return new Prodotto(this);
		}
	}
	
	private Prodotto(Builder build){
		nome = build.nome;
		prezzo = build.prezzo;
		descrizione = build.descrizione;
		produttore = build.produttore;
		categoria = build.categoria;
		listaFornitori = build.listaFornitori;
	}
	
	@Override public String toString(){
		StringBuilder prodottoString = new StringBuilder();
		prodottoString.append(nome + " " + prezzo + " " + produttore + " " + descrizione + " " + categoria 
				+ " " + getListaFornitoriAsString());
		
		return prodottoString.toString();
	}
}
