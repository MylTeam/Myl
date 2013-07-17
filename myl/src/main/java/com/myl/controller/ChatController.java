package com.myl.controller;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

import com.myl.modelo.Carta;
import com.myl.modelo.Deck;
import com.myl.modelo.DeckCartaId;
import com.myl.negocio.CartaNegocio;
import com.myl.negocio.DeckNegocio;
import com.opensymphony.xwork2.ActionSupport;

@Named
@Results({ @Result(name = "success", type = "redirectAction", params = {
		"actionName", "chat" }) })
public class ChatController extends ActionSupport {

	private static final long serialVersionUID = 8585016072024421730L;
	private Integer idSel;
	
	private Deck deck;
	private DeckNegocio deckNegocio;

	private List<Carta> deck1;
	
	
	@SkipValidation
	public HttpHeaders index() {
		
		deck=deckNegocio.findById(1);
		deck1=new ArrayList<Carta>();
				
		int count=0;
		for(Carta carta:deck.getCartas()){									
						
			for(int i=0;i<deck.getDeckCartas().get(count).getCartaQt();i++){
				Carta aux=(Carta) carta.clone();
				aux.setIdTemp(i);			
				deck1.add(aux);
			}
			count++;
		}

		return new DefaultHttpHeaders("index").disableCaching();
	}

	public Integer getIdSel() {
		return idSel;
	}

	public void setIdSel(Integer idSel) {
		this.idSel = idSel;
	}

	public Deck getDeck() {
		return deck;
	}

	public void setDeck(Deck deck) {
		this.deck = deck;
	}

	public DeckNegocio getDeckNegocio() {
		return deckNegocio;
	}

	public void setDeckNegocio(DeckNegocio deckNegocio) {
		this.deckNegocio = deckNegocio;
	}

	public List<Carta> getDeck1() {
		return deck1;
	}

	public void setDeck1(List<Carta> deck1) {
		this.deck1 = deck1;
	}
	

}
