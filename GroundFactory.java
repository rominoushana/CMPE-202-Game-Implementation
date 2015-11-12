import greenfoot.*;

/**
 * Write a description of class Tile here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GroundFactory
    {
        public ground getGround(int type)
        {  
         switch(type)
         {
             case 0:
                return new ground(); 
      
             case 1:
                return new ground("brown");
             case 2:
                 return new ground("spring");
                
             default: 
                return new ground();
             
         }
            
           
        }    
    }
