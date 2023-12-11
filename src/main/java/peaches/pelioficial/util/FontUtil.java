/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package peaches.pelioficial.util;

import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.io.InputStream;

/**
 *
 * @author mtx0v
 */
public class FontUtil {
    public static void applyFontToComponent(Component component, Font font){
        if(component instanceof Container){
            for(Component child : ((Container) component).getComponents()){
                applyFontToComponent(child, font);
            }
        }
        try{
            component.setFont(font);
        }catch (Exception e){
            
        }
    }
    
    public static Font loadFontFromResources(String path, float size){
        try{
            InputStream is = FontUtil.class.getResourceAsStream(path);
            Font font = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(size);
            return font;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
