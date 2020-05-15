package com.example.shoppinglist.data

import com.example.shoppinglist.logic.ShoppingNote

interface NotesDAO {

    fun all(): List<ShoppingNote>                       // Devuelve todos los elementos
    fun element(id: Int): ShoppingNote                  // Devuelve el elemento dado su id
    fun create(note: ShoppingNote): Boolean             // Añade el elemento indicado
    fun delete(id: Int): Boolean                        // Elimina el elemento con el id indicado
    fun length(): Int                                   // Devuelve el número de elementos
    fun update(id: Int, note: ShoppingNote): Boolean    // Reemplaza un elemento

}