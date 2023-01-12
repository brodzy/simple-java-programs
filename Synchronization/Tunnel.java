import java.util.concurrent.Semaphore;
public class Tunnel{
	public static void main(String [] args) throws InterruptedException {
		//Creating semaphore
		Semaphore semaphore = new Semaphore(1);
		
		//Sending semaphore to left and right bound threads
		Left leftBound = new Left(semaphore);
		Right rightBound = new Right(semaphore);
		
		//Starting and joining threads
		leftBound.start();
		rightBound.start();
		leftBound.join();
		rightBound.join();
		
	}

}

class Left extends Thread{
	//Var
	Semaphore sem;
	
	//Constructor
	public Left(Semaphore sem) {
		this.sem = sem;
	}
	
	@Override
	public void run() {
		//Creating even numbered cars
		int car = 1;
		
		//Setting times for print statements and tunnel
		long fixedTime = 2000;
		long randTimes = (long) (Math.random() * 2000);
		
		for(;;) {//Running thread until user terminates program
			try {
				//Beginning section
				System.out.println("Right-bound car " + car + " wants to enter the tunnel.");
				Thread.sleep(fixedTime);
				
				//Critical section
				sem.acquire();
				System.out.println("Right-bound car " + car + " is in the tunnel.");
				Thread.sleep(randTimes);//Simulating random times to go through tunnel
				
				//End section
				System.out.println("Right-bound car " + car + " is exiting the tunnel.");
				sem.release();
				Thread.sleep(fixedTime);
				
			} catch (InterruptedException e) {
				System.out.println("There was a problem with the thread!");
				e.printStackTrace();
			}
			
			//Increments car number to be even
			car = car + 2;
		}
	}

}

class Right extends Thread{
	//Var
	Semaphore sem;
	
	//Constructor
	public Right(Semaphore sem) {
		this.sem = sem;
	}
	
	@Override
	public void run() {
		//Creating odd numbered cars
		int car = 0;
		
		//Setting times for print statements and tunnel
		long fixedTime = 2000;
		long randTimes = (long) (Math.random() * 3000);
		
		for(;;) {//Running thread until user terminates program
			try {
				//Beginning section
				System.out.println("Left-bound car " + car + " wants to enter the tunnel.");
				Thread.sleep(fixedTime);
				
				//Critical section
				sem.acquire();
				System.out.println("Left-bound car " + car + " is in the tunnel.");
				Thread.sleep(randTimes);//Simulating random times to go through tunnel
				
				//End section
				System.out.println("Left-bound car " + car + " is exiting the tunnel.");
				sem.release();
				Thread.sleep(fixedTime);
				
			} catch (InterruptedException e) {
				System.out.println("There was a problem with the thread!");
				e.printStackTrace();
			}
			
			//Increments car number to be odd
			car = car + 2;
		}
	}

}

