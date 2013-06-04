package cn.edu.nju.clubunion.servlet.helper;

import javax.servlet.http.HttpServletRequest;

import cn.edu.nju.clubunion.abstractEntity.AContainerBlock;
import cn.edu.nju.clubunion.helper.CUH;

public abstract class AContentProcessor {
	
    protected String query;
    protected HttpServletRequest request;
    protected int containerId;
    
    public AContentProcessor(String query,HttpServletRequest request,int containerId)
    {
    	this.query=query;
    	this.request=request;
    	this.containerId=containerId;
    }

	public abstract boolean hasContentChanged();

	public abstract void editContent();

	public abstract void createContent();
	
	public boolean getRequestAvailable(String s) {
		return CUH.getParameter(query,s) == null ? false : true;
	}

}
