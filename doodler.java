import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class doodler here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class doodler  extends Actor
{
    float ys, xs;
    int x,y;
    boolean canMove;
    GreenfootImage left = new GreenfootImage("doodler.png");
    GreenfootImage right = new GreenfootImage("doodler.png");
    GreenfootImage shooting = new GreenfootImage("shooting.png");
    GroundFactory groundFactory=new GroundFactory();
    ground Ground = new ground();
    int scrollSpeed;
    int hits = 0;
    int x1;
    int x2;
    int y1,y2;
    int groundChance;
    
    public static int groundCount=0;
    /**
     * Act - do whatever the doodler wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */

    public doodler()
    {
        left.mirrorHorizontally();
        shooting.scale(25,40);
        canMove = true;
    }

    protected void addedToWorld(World world)
    {
        x = getX();
        y = getY();
    }

    public doodler(boolean moveable)
    {
        canMove = moveable;
    }

    public void act() 
    {
        x = getX();
        y = getY();

        ((doodleWorld) getWorld()).doodleX = getX();

        if(((doodleWorld) getWorld()).fall==false)
        {
            bounce();
        }
        if(canMove)
            keys();

        if(ys>11)
            ys = 10;

        setLocation(x+(int)xs, y+(int)ys);

        gravity();
        wrapAround();

        if(canMove & y < 400)
            scrollUp();

        if(y>400)
            fall();

        ((doodleWorld) getWorld()).height = hits;

        scout();
    }    

    public void gravity()
    {
        ys += 0.3f;
    }

    public void bounce()
    {
        ground Ground = (ground) getOneIntersectingObject(ground.class);
        if(Ground != null & ys > 0)
        {
            ys = -10;
            if(canMove & !Ground.hasBeenBounced)
            {
                Ground.hasBeenBounced=true;
                groundChance=Greenfoot.getRandomNumber(100);
                y1=getRandomY(groundCount%350);
                y2=y1+100;    
                
                //generate brown tiles between 0-30      
                if(groundChance <=  30 )
                {
                    x1=Greenfoot.getRandomNumber(300);
                    x2=getRandomX(x1);
                    groundCount++;
                    //1 => Brown tile
                    getWorld().addObject(groundFactory.getGround(1), x1, y1);
                   
                    groundCount++;
                    
                    getWorld().addObject(groundFactory.getGround(0), x2, y2);
                    
                }
                  
                    //generate spring tile between 30-40 
                else if(groundChance <= 40)   
                    {
                    x1=Greenfoot.getRandomNumber(300);
                    x2=getRandomX(x1);
                    groundCount++;
                    //2 => spring Tile
                    getWorld().addObject(groundFactory.getGround(2), x1, y1);
                    groundCount++;
                    getWorld().addObject(groundFactory.getGround(0), x2, y2);
                    
                }
                else
                    {
                    x1=Greenfoot.getRandomNumber(300);
                    x2=getRandomX(x1);    
                      //generate green tiles   
                    groundCount++;
                    //0 => Green tile
                    getWorld().addObject(groundFactory.getGround(0), x1, y1);
  
                    groundCount++;  
                    getWorld().addObject(groundFactory.getGround(0), x2, y2);
                    
                    }    
                    
            }
        }
    }
    //avoid overlapping of tiles
    public int getRandomX(int randomX1)
    {
        int randomX2=Greenfoot.getRandomNumber(300);
        while(randomX2==randomX1)
        {
         randomX2= Greenfoot.getRandomNumber(300);
        }
        
        return randomX2;
       
    }    

    public int getRandomY(int count)
    {
        //System.out.println(((count*20)+count)/100);
        return ((count*20)+count)/100; 
    }    
    public void keys()
    {
        if(Greenfoot.isKeyDown("right"))
        {
            xs += 0.25f;
            setImage(right);
        }
        if(Greenfoot.isKeyDown("left"))
        {
            xs -= 0.25f;
            setImage(left);
        }
        if(!Greenfoot.isKeyDown("left") & !Greenfoot.isKeyDown("right") & xs != 0)
        {
            xs = 0;
        }
        if(Greenfoot.isKeyDown("space") & getWorld().getObjects(ammo.class).isEmpty())
        {
            setImage(shooting);
            getWorld().addObject(new ammo(), x, y);
        }
    }

    public void wrapAround()
    {
        if(x>300)
        {
            setLocation(0, getY());
        }
        if(x<0)
        {
            setLocation(300,getY());
        }
    }

    public void scrollUp()
    {
        if(y<=200 & y>100)
        {
            ((doodleWorld) getWorld()).scrollSpeed = (int) -ys;
            ((doodleWorld) getWorld()).scroll = true;
            hits++;
        }
        else if(y<=100)
        {
            ((doodleWorld) getWorld()).scrollSpeed = (int) -ys*2;
            ((doodleWorld) getWorld()).scroll = true;
            hits++;
        }

        else
        {
            ((doodleWorld) getWorld()).scroll = false;
        }
    }

    public void fall()
    {
        ((doodleWorld) getWorld()).fall = true;
        ((doodleWorld) getWorld()).scrollSpeed = (int) -ys;
        groundCount=0;
    }

    public void scout()
    {
        if(y<0 & getWorld().getObjects(arrow.class).isEmpty())
        {
            getWorld().addObject(new arrow(), getX(), 20);
        }
        if(y>0 & !getWorld().getObjects(arrow.class).isEmpty())
        {
            getWorld().removeObjects(getWorld().getObjects(arrow.class));
        }
    }
}
