/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Zhenya
 */
class Pair<T0, T1> {
    private T0 first;
    private T1 second;
    
    public Pair(T0 first, T1 second){
        this.first = first;
        this.second = second;
    }
    
    public T0 getFirst(){
        return first;
    }
    public T1 getSecond(){
        return second;
    }
}
