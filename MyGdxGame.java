package com.mycompany.mygame;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.Rectangle;
import java.nio.file.attribute.PosixFileAttributes;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;
import java.util.Random;
import java.util.ArrayList;
import java.util.*;
import com.badlogic.gdx.math.*;


public class MyGdxGame implements ApplicationListener
{
	float pontos;
	int distancia=500;
	Texture obj;
     List<Rectangle> cacto;
    BitmapFont font;
	TextureRegion spr1;
	TextureRegion spr2;
	TextureRegion spr3;
	TextureRegion spr4;
	Texture texture4;
	Texture texture2;
	Texture texture3;
	Texture texture;
	SpriteBatch batch;
	float velocidadeY=0;
Animation anim;
float fds;
Rectangle pos;
float velocidade=40;
OrthographicCamera cam;
	@Override
	public void create()
	{
		cacto = new ArrayList<Rectangle>();
		font = new BitmapFont();
		cam=new OrthographicCamera();
		cam.setToOrtho(false, 300,500);
		
		pos = new Rectangle(0,0,64,64);
		
		obj=new Texture(Gdx.files.internal("obj.png"));
	   
		
		int x = 400;
		for (int i = 0; i < 60; i++)
		{
			cacto.add(new Rectangle (x, 63,0,0));
			x += distancia + new Random().nextInt(100);
		}
		
		
		texture4 = new Texture(Gdx.files.internal("s1.png"));
		texture2 = new Texture(Gdx.files.internal("s2.png"));
		texture3 = new Texture(Gdx.files.internal("s3.png"));
		texture = new Texture(Gdx.files.internal("s4.png"));
		spr1=new TextureRegion(texture4,180,180);
		spr2=new TextureRegion(texture2,180,180);
		spr3=new TextureRegion(texture3,180,180);
		spr4=new TextureRegion(texture,180,180);
		anim=new Animation(0.050f, spr1,  spr3, spr4);
		//anim.setPlayMode(Animation.PlayMode.LOOP);
		batch = new SpriteBatch();
		
	
	}

	@Override
	public void render()
	{        
		pontos = pos.x / 15;
	    Gdx.gl.glClearColor(1, 1, 1, 1);
	    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		cam.update();
		batch.setProjectionMatrix(cam.combined);
		batch.begin();
		font.setColor(Color.BLACK);
	   
		font.draw(batch, (int) (pontos) + " pontos", cam.position.x - 10, 30);
		batch.draw(anim.getKeyFrame(fds,true),pos.x,pos.y,180,180);
		for (Rectangle r : cacto)
			batch.draw(obj, r.x, r.y);
		
		
		batch.end();
		cam.translate(41*Gdx.graphics.getDeltaTime(),0);
		fds+=Gdx.graphics.getDeltaTime();
		
		
	    if (Gdx.input.isTouched())
			if (pos.y==0)
				velocidadeY=470;
	           if (pos.y>0)
				   velocidade=48;
				   else
					   velocidade=40;
		for (Rectangle r : cacto)
			if (r.overlaps(pos) && r.getCenter(new Vector2()).dst(pos.getCenter(new Vector2())) < 120)
			{
				velocidade=0;
				velocidadeY=0;
				pontos=0;
				pos.x=-80;
				cam.position.x=0;
				
				
				
			}
					 /* if(Intersector.overlaps(pos, ){
						   velocidade=0;
						   velocidadeY=0;
						 
						   batch.dispose();
						   
						  
					   }*/
					   
			   
	    pos.x += velocidade*Gdx.graphics.getDeltaTime();
	
		pos.y += velocidadeY * Gdx.graphics.getDeltaTime();
		velocidadeY -= 450* Gdx.graphics.getDeltaTime();
		if (pos.y < 0) 
		{
			pos.y = 0;
			velocidadeY = 0;
		}
		
		}

	@Override
	public void dispose()
	{
	}

	@Override
	public void resize(int width, int height)
	{
	}

	@Override
	public void pause()
	{
	}

	@Override
	public void resume()
	{
	}
	
	
	
	
}

