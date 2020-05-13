package com.example.shoppinglist.data

interface NotesDAO {

    fun note(id: Int): ShoppingNote                     // Devuelve el elemento dado su id
    fun add(note: ShoppingNote): Boolean                // Añade el elemento indicado
    fun new(): Int                                      // Añade un elemento en blanco y devuelve su id
    fun delete(id: Int): Boolean                        // Elimina el elemento con el id indicado
    fun length(): Int                                   // Devuelve el número de elementos
    fun update(id: Int, note: ShoppingNote): Boolean    // Reemplaza un elemento

}