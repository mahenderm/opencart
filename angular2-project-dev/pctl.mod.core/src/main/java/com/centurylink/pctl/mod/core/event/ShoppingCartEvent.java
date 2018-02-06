package com.centurylink.pctl.mod.core.event;


import com.centurylink.pctl.mod.core.model.user.LoggedUser;

public class ShoppingCartEvent<T> extends AbstractShoppingCartEvent
{
    private static final long serialVersionUID = 1L;
    public ShoppingCartEvent() {
		super();
	}

    //public Boolean userInit = false;
  //  public Boolean orderInit= false;

    private T eventAttributes;

	public ShoppingCartEvent(String Id, EventHeader header, LoggedUser userInfo, T eventAttributes)
    {
        this.id = Id;
		this.header = header;
		this.userInfo = userInfo;
        this.eventAttributes= eventAttributes;
    }


    public T getEventAttributes() {
        return eventAttributes;
    }

    public void setEventAttributes(T eventAttributes) {
        this.eventAttributes = eventAttributes;
    }

  /*  public Boolean getUserInit() {
        return userInit;
    }

    public void setUserInit(Boolean userInit) {
        this.userInit = userInit;
    }*/

 /*   public Boolean getOrderInit() {
        return orderInit;
    }

    public void setOrderInit(Boolean orderInit) {
        this.orderInit = orderInit;
    }*/

}
