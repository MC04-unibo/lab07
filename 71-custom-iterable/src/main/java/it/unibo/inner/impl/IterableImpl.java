package it.unibo.inner.impl;

import java.util.Iterator;

import it.unibo.inner.api.IterableWithPolicy;
import it.unibo.inner.api.Predicate;

public class IterableImpl<T> implements IterableWithPolicy<T>{
    private T[] array;
    private Predicate<T> predicate;

    public IterableImpl(T[] array){
        this.array = array;
        this.predicate = new Predicate<T>() {
            @Override
            public boolean test(T elem) {
                return true;    
            }
        };
    }

    public IterableImpl(T[] array, Predicate<T> predicate){
        this.array = array;
        this.predicate = predicate;
    }

    public Iterator<T> iterator(){
        return new IteratorImpl(array,predicate);
    }

    @Override
    public void setIterationPolicy(Predicate<T> filter) {
        this.predicate = filter;
    }

    class IteratorImpl implements Iterator<T>{
        private int current;
        private T[] array;
        private Predicate<T> predicate;

        public IteratorImpl(T[] array, Predicate<T> predicate){
            this.current = 0;
            this.array = array;
            this.predicate = predicate;
        }

        @Override
        public boolean hasNext() {
            return elementHasNext(current);
        }

        private boolean elementHasNext(int elem){
            if(elem == array.length){
                return false;
            }

            if(predicate.test(array[elem])){
                return true;
            }else{
                return elementHasNext(elem+1);
            }
        }

        @Override
        public T next() {
            if(current >= array.length){
                return null;
            }

            if(array != null && array.length > 0){
                if(this.hasNext()){
                    current++;
                    if(predicate.test(array[current-1])){
                        return array[current-1];
                    }else{
                        return next();
                    }
                }else{
                    return null;
                }
            }else{
                return null;
            }
        }
    }
}