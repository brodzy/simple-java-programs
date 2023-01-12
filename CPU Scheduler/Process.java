
public class Process implements Comparable<Process>{
		//Vars
		int pid;//0-10
		int priority;//1-10
		int burstLength;//20-100
		int waitTime;
		
		//Constructor
		public Process(int pid,  int priority, int burstLength) {
	        this.pid = pid;
	        this.priority = priority;
	        this.burstLength = burstLength;
		}
		
		//Getters and setters
		public int getPid() {
	        return pid;
		}
		public void setPid(int pid) {
	        this.pid = pid;
		}
		public int getPriority() {
	        return priority;
		}
		public void setPriority(int priority) {
	        this.priority = priority;
		}
		public int getBurstLength() {
	        return burstLength;
		}
		public void setBurstLength(int burstLength) {
	        this.burstLength = burstLength;
		}
		
	    public int getWaitTime(){
	        return this.waitTime;
	    }

	    public void wait(int i){
	        this.waitTime += i;
	    }

		@Override
		public int compareTo(Process p) {
			int comparePid = ((Process)p).getPid();
	        /* For Ascending order*/
	        return this.pid - comparePid;
		}
		
		
}
