import greenfoot.*;

/**
 * Write a description of class worldMemento here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class worldMemento
{
   private doodleWorld doodleWorld;
   
   public worldMemento(doodleWorld doodle)
   {
      this.doodleWorld=doodle;
   }
   
   public doodleWorld getWorld()
   {
      return doodleWorld;
   }   
}
