package net.nenko.lib;

import java.lang.reflect.Array;
import java.lang.reflect.ParameterizedType;

public class SlidingIndexArray<E> {

	private long baseIx = -1;	// first used index
	private int used = 0;		// number of used slots
	private E[] slots;	// storage to keep objects

	@SuppressWarnings("unchecked")
	public SlidingIndexArray(Class<E> c, int size) {
		this.slots = (E[]) Array.newInstance(c, size);
	}

	public void put(long ix, E e) {
		this.slots[used++] = e;
	}

	public E get(long ix) {
		return this.slots[used++];
	}

}
