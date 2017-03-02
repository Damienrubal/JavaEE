/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Dam's
 */
@Remote
public interface StatefullApplication {
     
    public boolean Connected( String login , String password);
    public List<String> SetUp();
    public boolean RemoveAttribute(String name,String value);
    public boolean Insert(String login,String name,String value);
    public boolean Update(String id, String name , String value);
    String getBeanName();
}
