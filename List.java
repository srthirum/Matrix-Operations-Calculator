

public class List {
   private class Node {
      Object entry;
      Node prev;
      Node next;
      
      // Creates a Node with Object entry.
      Node(Object entry) {
         this.entry = entry;
         prev = null;
         next = null;
      }
      
      // Creates a Node with Object entry, Node prev, and Node next.
      Node (Object entry, Node prev, Node next) {
         this.entry = entry;
         this.prev = prev;
         this.next = next;
      }

      // Returns String representation of the data property.
      public String toString() {
         return entry.toString();
      }
      
      // Returns true if two Nodes data properties are equal.
      public boolean equals(Object x) {
         boolean eq = false;
         Node that;
         if(x instanceof Node) {
            that = (Node) x;
            eq = (this.entry.equals(that.entry));
         }
         return eq;
      }
   }

   private Node front;
   private Node back;
   private Node cursor;
   private int length;
   private int index;

   // Creates a new empty List.
   List() {
      front = back = cursor = null;
      length = 0;
      index = -1;
   }
   
   // Returns the number of elements in this List.
   int length() {
      return length;
   }

   // If cursor is defined, returns the index of the cursor
   // element, otherwise returns -1.
   int index() {
      return index;
   }

   // Returns front element.
   // Pre: length() > 0
   Object front() {
      if(length < 1)
         throw new RuntimeException("List Error: front() called on empty List");
      return front.entry;
   }

   // Returns back element.
   // Pre: length() > 0
   Object back() {
      if(length < 1)
         throw new RuntimeException("List Error: back() called on empty List");
      return back.entry;
   }

   // Returns cursor element.
   // Pre: length() > 0
   Object get() {
      if(index < 0)
         throw new RuntimeException("List Error: get() called with undefined index on List");
      if(length < 1)
         throw new RuntimeException("List Error: get() called on an empty List");
      return cursor.entry;
   }

   // Returns true if this List and L are the same integer
   // sequence. The cursor is ignored in both lists.
   boolean equals(List L) {
      if(L.length() != length) {
         return false;
      }
      Node cfront = L.front;
      Node temp = front;
      while(cfront != null && temp != null) {
         if(!(cfront.equals(temp)))
            return false;
         cfront = cfront.next;
         temp = temp.next;
      }
      return true;
   }

   // Resets this List to its original empty state.
   void clear() {
      front = back = cursor = null;
      length = 0;
      index = -1;
   }
   
   // If List is non-empty, places the cursor under the front
   // element, otherwise does nothing.
   void moveFront() {
      if(length > 0) {
         cursor = front;
         index = 0;
      } 
   }

   // If List is non-empty, places the cursor under the back
   // element, otherwise does nothing.
   void moveBack() {
      if(length > 0) {
         cursor = back;
         index = length - 1;
      }
   }

   // If cursor is defined and not at front, moves cursor one step
   // toward front of this List, if cursor is defined and at front,
   // cursor becomes undefined, if cursor is undefined does nothing.
   void movePrev() {
      if(cursor != null && index != 0) {
         cursor = cursor.prev;
         --index;
      }
      else if(cursor != null && index == 0) {
         cursor = null;
         index = -1;
      }
   }
   
   // If cursor is defined and not at back, moves cursor one step
   // toward back of this List, if cursor is defined and at back,
   // cursor becomes undefined, if cursor is undefined does nothing.
   void moveNext() {
      if(cursor != null && index != length - 1) {
         cursor = cursor.next;
         ++index;
      }
      else if(cursor != null && index == length - 1) {
         cursor = null;
         index = -1;
      }
   }

   // Insert new element into this List. If List is non-empty,
   // insertion takes place before the front element.
   void prepend(Object entry) {
      Node temp = new Node(entry, null, front);
      if(front == null)
         back = temp;
      else
         front.prev = temp;
      front = temp;
      ++length;
   }

   // Insert new element into this List. If List is non-empty,
   // insertion takes place after back element.
   void append(Object entry) {
      Node temp = new Node(entry, back, null);
      if(front == null)
         front = temp;
      else
         back.next = temp;
      back = temp;
      ++length;
   }

   // Insert new element before cursor.
   // Pre: length() > 0, index() >= 0
   void insertBefore(Object entry) {
      if(index < 0)
         throw new RuntimeException("List Error: insertBefore() called with an undefined index on List");
      if(length < 1)
         throw new RuntimeException("List Error: insertBefore() called on an empty List");
      Node temp = new Node(entry, cursor.prev, cursor);
      if(cursor.prev != null)
         cursor.prev.next = temp;
      else
         front = temp;
      cursor.prev = temp;
      ++length;
   }
   
   // Inserts new element after cursor.
   // Pre: length() > 0, index() >= 0
   void insertAfter(Object entry) {
      if(index < 0)
         throw new RuntimeException("List Error: insertAfter() called with an undefined index on List");
      if(length < 1)
         throw new RuntimeException("List Error: insertAfter() called on an empty List");
      Node temp = new Node(entry, cursor, cursor.next);
      if(cursor.next != null)
         cursor.next.prev = temp;
      else
         back = temp;
      cursor.next = temp;
      ++length; 
   }
   
   // Deletes the front element.
   // Pre: length() > 0
   void deleteFront() {
      if(length < 1)
         throw new RuntimeException("List Error: deleteFront() called on an empty List");
      if(cursor == front) {
         cursor = null;
         index = -1;
      }
      front = front.next;
      front.prev = null;
      --length;
   }
   
   // Deletes the back element.
   // Pre: length() > 0
   void deleteBack() {
      if(length < 1)
         throw new RuntimeException("List Error: deleteBack() called on an empty List");
      if(cursor == back) {
         cursor = null;
         index = -1;
      }
      back = back.prev;
      back.next = null;
      --length;
   }
   
   // Deletes cursor element, making cursor undefined.
   // Pre: length() > 0, index() >= 0
   void delete() {
      if(index < 0)
         throw new RuntimeException("List Error: delete() called with an undefined index on List");
      if(length < 1)
         throw new RuntimeException("List Error: delete() called on an empty List");
      if(cursor == back)
         deleteBack();
      else if(cursor == front)
         deleteFront();
      else {
         cursor.prev.next = cursor.next;
         cursor.next.prev = cursor.prev;
         cursor = null;
         index = -1;
         --length;
      }
   }

   // Overrides Object's toString method. Returns a String
   // representation of this List consisting of a space
   // separated sequence of integers, with front on left.
   public String toString() {
      Node temp = front;
      String print = new String();
      String space = "";
      while(temp != null) {
         print += space + temp.entry;
         temp = temp.next;
         space = " ";
      }
      return print;
   }
}