/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hykyx;

import hykyx.ServiceTypes;

/**
 *
 * @author Franklin Xam
 */
public class Server {
    String IP;
    private final int tickTimems = 1;
    int connections;
    int priority = 20;
    boolean isActive = true;
    ServiceTypes[] services = new ServiceTypes[4];
    int numberOfServices = 0;
    public Server(String ip) {
        connections = 0;
        IP = ip;
    }
    public Server(int connections, int priority) {
        this.connections = connections;
        this.priority = priority;
    }
    public void addServices(ServiceTypes s) {
        if(numberOfServices < services.length) {
            services[numberOfServices] = s;
        }
    }
    public void makeConnection(int ticks) {
        connections++;
        long now = System.currentTimeMillis(), delta = 0;
        
        while(ticks * tickTimems > delta) {
            System.out.println("Processing");
            long past = now;
            now = System.currentTimeMillis();
            delta = now - past;
        }
        removeConnection();   
    }
    public void removeConnection() {
        if(connections > 0) connections--;
    }
    public void toggleServer() {
        this.isActive = false;     
    }
    
    
    public String toString() {
        String res = String.format(" Server IP: %s"
                + "\n Active Connections:  %d"
                + "\n Priority: %d"
                + "\n Status: %B"
                + "\n Services: ", IP, connections, priority, isActive);
        for(int i = 0; i < services.length; i++) {
            res += " " + services[i];
        }
        return res;
    }
}
