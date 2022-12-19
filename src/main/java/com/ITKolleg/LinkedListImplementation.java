
package com.ITKolleg;

/**
 *
 */
public class LinkedListImplementation {

    public static void main(String[] args) {

        System.out.println("Starte belegen der Liste");
        MyLinkedListV1<String> myList = new MyLinkedListV1<>();
        myList.add("String 1");
        myList.add("String 2");
        myList.add("String 3");
        myList.add("String 4");
        myList.add("String 5");
        myList.add("String 6");

        System.out.println(myList);

        //Test removing an element at the head of the LinkedList
        System.out.println("Remove 1st element from List");
        myList.remove(0);
        System.out.println(myList);

        //Test removing an element at a negative index in the LinkedList
        System.out.println("Remove element at non-existent (-1) index List");
        myList.remove(-1);
        System.out.println(myList);

        //Test removing an element in the middle of the LinkedList
        System.out.println("Remove 3rd element from List");
        myList.remove(2);
        System.out.println(myList);

        //Test removing the last element of the LinkedList
        System.out.println("Remove last element from List");
        myList.remove(3);
        System.out.println(myList);

        //There is no 6th Element in this list; Test removing an element at an index out of bounds
        System.out.println("Remove 6th element from List");
        myList.remove(5);
        System.out.println(myList);

        //add a String at index 0; Test adding an element at the beginning of the LinkedList
        System.out.println("Add a new element (\"String 9\") to the List at index 0");
        myList.add(0, "String 9");
        System.out.println(myList);

        //add a String at index 2; Test adding an element in the middle of the LinkedList
        System.out.println("Add a new element (\"String 7\") to the List at index 2");
        myList.add(2, "String 6");
        System.out.println(myList);

        //add a String at index 5; Test adding an element as the last Element in the LinkedList
        System.out.println("Add a new element (\"String 8\") to the List at index 5");
        myList.add(5, "String 7");
        System.out.println(myList);

        /*add a String at index 7; Test adding an element at the index out of scope to the LinkedList
        System.out.println("Add a new element (\"String 10\") to the List at index 8");
        myList.add(7, "String 8");
        System.out.println(myList);*/


    }

}

class MyLinkedListV1<M> {

    public ElementContainer<M> head;

    /**
     * adds an element to a Container
     * @param string
     */
    public void add(M string) {
        ElementContainer<M> newElement = new ElementContainer<>(string);
        if (head == null) {
            head = newElement;
        } else {
            ElementContainer<M> lastElement = this.lastElement();
            lastElement.nextElement = newElement;
        }

    }

    /**
     * adds a provided element to a Container at the specified index
     *
     * @param index
     * @param string
     */
    public void add(int index, M string) {

        ElementContainer<M> newElement = new ElementContainer<>(string);
        ElementContainer<M> currentElement = head;
        ElementContainer<M> previousElement = null;
        int counter = 0;


        //if the List is empty add new element at head
        if (head == null && index == 0) {
            head = newElement;
            System.out.println("New element added to list at index " + index);
        }

        if ((counter <= index - 2)) {
            assert currentElement != null;
            if (currentElement.nextElement == null) { //the element can't be added at the list at that index

                // set the current element's next to the new element (effectively adding the new element to the end of the list)
                System.out.println("New element can not be added to List at this index " + index + ".\n Index Out of Bounds");
            }
        }

        //case the container is not empty
        while (currentElement != null) {

            if (counter == index) {
                // the new element is added at the head, index 0
                if (previousElement == null) {
                    head = newElement;
                    newElement.nextElement = currentElement;
                    System.out.println("New element added to container at index " + index);
                }
                // index is greater than 0 )
                else if (previousElement != null) {
                    previousElement.nextElement = newElement; //adds the element to the container at index position
                    newElement.nextElement = currentElement;  //redirects the reference from the current element at index to the new element at index
                    System.out.println("New element added to container at index " + index);
                }
                //prevents an infinite while loop
                break;

            }
            if (counter == (index - 1) && currentElement.nextElement == null) { //this means that the element should be added at the end of the container
                // set the current element's next to the new element (effectively adding the new element to hte end of the container)
                if (previousElement != null) {
                    currentElement.nextElement = newElement;
                    System.out.println("New element added to container at index " + index);
                }
                //prevents an infinite while loop
                break;
            } else {
                //iterate to next element
                previousElement = currentElement;
                currentElement = currentElement.nextElement;
                //increase the counter by 1
                counter++;
            }
        }
    }

    /**
     * removes an element from a container at the index provided
     * @param index
     */
    public void remove (int index) {

        ElementContainer<M> currentElement = head;
        ElementContainer<M> previousElement = null;
        int counter = 0; //to keep track of the elements in container

        //case the index is 0, element is head, reset next element to head
        if((currentElement != null) && (index == 0)){
            head = currentElement.nextElement;
            System.out.println("The element at index " + index + " was found and deleted");
        }

        //case the index is greater than 0(element not head)
        while(currentElement != null){
            //the current element is the one to be deleted
            if(counter == index){
                // set the previous element's next to the current element's next (effectively skips current element)
                if (previousElement != null) {
                    previousElement.nextElement = currentElement.nextElement;
                    System.out.println("The element at index " + index + " was found and deleted");
                }
                //prevents an infinite while loop
                break;
            }else{
                //iterate to next element
                previousElement = currentElement;
                currentElement = currentElement.nextElement;
                //increase the counter by 1
                counter++;
            }
        }
        //if the element is not found after complete iteration
        if(currentElement == null){
            System.out.println("No element at index " + index + " was found!\nNo element was deleted!");
        }
    }

    /**
     * returns the last element of a list in a container
     * @return
     */

    private ElementContainer<M> lastElement() {
        ElementContainer<M> lastElement = head;

        if(lastElement != null){
            while(lastElement.nextElement != null){
                lastElement = lastElement.nextElement;
            }
        }

        return lastElement;
    }

    /**
     * converts a list to a string for printing
     *
     * @return
     */
    @Override
    public String toString(){
        String returnString = "\nCurrent Container Elements: \n";

        ElementContainer<M> currentElement = head;

        int counter = 0;
        while(currentElement != null){
            returnString += String.format("Element %d: %s %n", counter, currentElement.data);
            currentElement = currentElement.nextElement;
            counter++;
        }

        return returnString;
    }

}

/**
 *
 * @param <D>
 */
class ElementContainer<D> {

    public D data;
    public ElementContainer<D> nextElement;

    public ElementContainer(D data){
        this.data = data;
    }

}
