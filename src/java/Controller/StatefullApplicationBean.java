/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.DataAccess;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import javax.ejb.Stateful;

/**
 *
 * @author Dam's
 */
@Stateful
public class StatefullApplicationBean implements StatefullApplication {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    private DataAccess dataAccess;
    private boolean result = false; 
    
  private static final AtomicLong COUNTER = new AtomicLong();

  private final long id = COUNTER.getAndIncrement();



  @Override
  public String getBeanName() {
    return "Bean:" + id;
  }
    
   @Override
       public boolean Connected( String login , String password){
        dataAccess = new DataAccess();
        result = dataAccess.checkPassword(login, password);
        if(result){
            result=true;
        }
        return result ; 
    }
    
      @Override
    public List<String> SetUp(){
        List<String>message = new ArrayList<String>();    
        message= dataAccess.display();   
        return message;
    }
  
    
    @Override
    public boolean RemoveAttribute(String name,String value){
           if(!("".equals(name) || "".equals(value))){
               
                result=dataAccess.delete(name, value);
            }
        return result;
    }
    
    @Override
    public boolean Insert(String login,String name,String value){
        if(!("Login".equals(name) || "Password".equals(name))){
           result =dataAccess.add(login,name,value);
          }  
       
        return result;
    }
    
    @Override
    public boolean Update(String id, String name , String value){
        
        if (!("".equals(name) || "".equals(value) || "".equals(id))) {
            result =dataAccess.update(id, name, value);
        }
        return result; 
    }
}
