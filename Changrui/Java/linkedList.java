import java.util.*;

//E在java中指泛型，只存放E对象用于约束array，
//你可以写一个泛型方法，该方法在调用时可以接收不同类型的参数。根据传递给泛型方法的参数类型，编译器适当地处理每一个方法调用。
//下面是定义泛型方法的规则：
//		1. 所有泛型方法声明都有一个类型参数声明部分（由尖括号分隔），该类型参数声明部分在方法返回类型之前（在下面例子中的<E>）。
//		2. 每一个类型参数声明部分包含一个或多个类型参数，参数间用逗号隔开。一个泛型参数，也被称为一个类型变量，是用于指定一个泛型类型名称的标识符。
//		3. 类型参数能被用来声明返回值类型，并且能作为泛型方法得到的实际参数类型的占位符。
//		4. 泛型方法体的声明和其他方法一样。注意类型参数只能代表引用型类型，不能是原始类型（像int,double,char的等）。
//https://www.runoob.com/java/java-basic-datatypes.html
//https://www.runoob.com/java/java-generics.html


//构建Node对象
class Node<E> {
	private E elem;
	private Node<E> next;
	/**
     * 构造方法
     */
	public Node(E element,Node<E>next){
		this.elem = element;
		this.next = next;
	}
	public Node(){
		this(null,null);//without parameter, set both as NULL
	}

	public void setElem(E element){
		this.elem = element;
	}

	public E getElem(){
		return this.elem;
	}

	public void setNext(Node<E> next){
		this.next = next;
	}

	public Node<E> getNext(){
		return this.next;
	}
}


public class SingleLinkedList<E>{
	private Node<E> head;
    private int size;


	public SingleLinkedList(){
		this.head = new Node<E>();
		this.size = 0;
	}

	public void addFirst(E element){
		Node<E> node = new Node <E> (element, null);
		node.setNext(this.head.getNext());
		this.head.setNext(node);
		this.size++;
	}

	public void addLast(E element){
		Node<E> node= new Node <E> (element, null);
		Node<E> current= this.head;
		while (current.getNext() != null){
			current=current.getNext();
		}
		current.setNext(node);
		this.size++;

	}

	public E getFirst(){
		if (this.head.getNext()==null){
			throw new NoSuchElementException();
		}
		return head.getNext().getElem();
	}

	public E getLast(){
		Node<E> current = this.head;
		while(current.getNext()!=null){
			current=current.getNext();
		}
		if (current==head){
			throw new NoSuchElementException();
		}
		return current.getElem();
	}

	public boolean isEmpty(){
		return this.size == 0? true : false;
	}

	public int size(){
		return this.size;
	}


}


