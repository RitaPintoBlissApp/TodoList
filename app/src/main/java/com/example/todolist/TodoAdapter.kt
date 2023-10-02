package com.example.todolist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class TodoAdapter(
    private val todos: MutableList<Todo>
): RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    class TodoViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) //mantém referências aos elementos visuais de cada item individual no RecyclerView.


    /*
    * O método onCreateViewHolder() é chamado pelo RecyclerView para criar
    * uma nova instância de TodoViewHolder quando necessário. Ele infla o layout
    *  do item individual usando o LayoutInflater e retorna uma instância de
    * TodoViewHolder com a visualização do item inflado.*/
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
       return TodoViewHolder(
           LayoutInflater.from(parent.context).inflate(
               R.layout.item_todo,
               parent,
               false
           )
       )
    }

    //adicionar um novo item todo à lista de itens (todos)
    fun addTodo(todo: Todo){
        todos.add(todo)
        notifyItemInserted(todo.size -1)
    }

    //adicionar um novo item Todo à lista de itens (todos)
    fun deleteDoneTodos(){
        todos.removeAll{ todo ->
            todo.isChecked
        }
        notifyDataSetChanged()
    }

    /*
    * sada para aplicar ou remover a formatação de "strike-through" (riscado)
    *  em um TextView com base no estado de seleção de um item. */
    private fun toggleStrikeThrough(tvTodoTitle: TextView, isChecked:Boolean){

        if(isChecked){
            tvTodoTitle.paintFlags = tvTodoTitle.paintFlags or STRIKE_THRU_TEXT_FLAG
        } else {
            tvTodoTitle.paintFlags = tvTodoTitle.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    /*
    O método onBindViewHolder() é chamado pelo RecyclerView para
     associar os dados de um item individual à visualização correspondente
     Ele obtém o item da lista todos com base na posição fornecida, atualiza
      os elementos visuais do item (como o título do todo e o estado da caixa
     de seleção), aplica a formatação de "strike-through" conforme necessário
     e configura um OnCheckedChangeListener para a caixa de seleção que atualiza o
     estado do item quando é alterado.
    * */
    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
            val curTodo = todos[position]
            holder.itemView.apply{
                tvTodoTitle.text = curTodo.title
                cbDone.ischecked = curTodo.isChecked
                toggleStrikeThrough(tvTodoTitle, curTodo.isChecked)
                cbDone.SetOnCheckedChangeListener{-, isChecked ->
                toggleStrikeThrough(tvTodoTitle, isChecked )
                curTodo.isChecked = !curTodo.isChecked}
            }

    }

    //retorna o número total de itens na lista todos.
    override fun getItemCount(): Int {
        return todos.size
    }
}