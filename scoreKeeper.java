import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class scoreKeeper here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class scoreKeeper  extends Actor
{
    GreenfootImage image = new GreenfootImage(100,10);
    /**
     * Act - do whatever the scoreKeeper wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    public scoreKeeper()
    {
        setImage(image);
    }
    
    public void act() 
    {
        image.clear();
        image.drawString(((doodleWorld) getWorld()).height + " doodles.", 10,10);
        setImage(image);
    }    
}
