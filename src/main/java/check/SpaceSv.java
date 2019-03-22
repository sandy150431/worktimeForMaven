package check;

public class SpaceSv {

	  public int getInteger(String no) {
		    if (no.equals("")) {
		    	no = "0";
		        return Integer.parseInt(no); //convert your string into integer 
		    } else {
		        return Integer.parseInt(no); // or what you want to return if string is Null
		    }
		}
	  public String isnull(String non){
		 if(non != null){
			 return non;
		 }else{
			 non = "null";
			 return non;
		 } 
	  }
	  public String pnnull(String pj){
		  String name = "請選擇專案";
			 if(pj.equals(name)){
				 pj = "null";
				 return pj ;
			 }else{
				 return pj;
			 } 
		  }
}
