import greenfoot.*;

/**
 * Write a description of class helpWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class helpWorld extends World
{
    public worldMemento memento;
    private doodleWorld doodleWorld;
    
    public helpWorld(worldMemento memento)
    {    
        super(300,400, 1); 
        setBackground("help.png");
        getBackground().setTransparency(255); 
        this.memento=memento;
    }
    public void act()
    {
        MouseInfo mouse = Greenfoot.getMouseInfo();
        if(mouse != null )
         {
                
                if(Greenfoot.mouseClicked(this))
            {
                if(mouse.getX()>=85 & mouse.getX()<=195
                && mouse.getY()>=345 & mouse.getY()<=396)
                {
                    this.doodleWorld=memento.getWorld();
                    Greenfoot.setWorld(doodleWorld);  
                }
            }
                
        }   
        
    }    
    
    

}
