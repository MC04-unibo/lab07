package it.unibo.inner.impl;

import java.util.Iterator;

import it.unibo.inner.api.IterableWithPolicy;
import it.unibo.inner.api.Predicate;

public class IterableImpl<T> implements IterableWithPolicy<T>{
    T[] array;

    public IterableImpl(T[] array){
        this.array = array;
    }

    public Iterator<T> iterator(){
        return new IteratorImpl(array);
    }

    @Override
    public void setIterationPolicy(Predicate<T> filter) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setIterationPolicy'");
    }

    class IteratorImpl implements Iterator<T>{
        private int current;
        private T[] array;
        IteratorImpl(T[] array){
            this.current = 0;
            this.array = array;
        }

        @Override
        public boolean hasNext() {
            return array.length != current && array.length > 0;
        }

        @Override
        public T next() {
            if(array != null && array.length > 0){
                if(this.hasNext()){
                    current++;
                    return array[current-1];
                }else{
                    return array[current];
                }
            }else{
                return null;
            }
        }
    }
}