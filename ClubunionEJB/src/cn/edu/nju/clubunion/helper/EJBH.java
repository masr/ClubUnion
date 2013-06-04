package cn.edu.nju.clubunion.helper;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;


public class EJBH {
	
	  public static <E>  List<E> getListOneByOne(List<E> source){
		  List<E> dest=new ArrayList<E>();
		  for(int i=0;i<source.size();i++)
		  {
			  dest.add(source.get(i));
		  }
		  return dest;
	  }

}
