package adt.rbtree;

import adt.bst.BSTImpl;
import adt.bt.Util;
import adt.rbtree.RBNode.Colour;

import java.util.ArrayList;
import java.util.List;

public class RBTreeImpl<T extends Comparable<T>> extends BSTImpl<T> implements RBTree<T> {

   public RBTreeImpl() {
      this.root = new RBNode<T>();
   }

   protected int blackHeight() {
      int result = 0;

      if (!this.isEmpty()) {
         result = blackHeightRecursive((RBNode<T>) this.root);
      }

      return result;
   }

   private int blackHeightRecursive(RBNode<T> node) {
      int result = 0;

      if (node != null && !node.isEmpty()) {
         if (node.getColour().equals(Colour.BLACK)) {
            result = 1 + Math.max(blackHeightRecursive((RBNode<T>) node.getLeft()),
                  blackHeightRecursive((RBNode<T>) node.getRight()));
         } else {
            result = Math.max(blackHeightRecursive((RBNode<T>) node.getLeft()),
                  blackHeightRecursive((RBNode<T>) node.getRight()));
         }
      }

      return result;
   }

   protected boolean verifyProperties() {
      boolean resp = verifyNodesColour() && verifyNILNodeColour() && verifyRootColour() && verifyChildrenOfRedNodes()
            && verifyBlackHeight();

      return resp;
   }

   /**
    * The colour of each node of a RB tree is black or red. This is guaranteed
    * by the type Colour.
    */
   private boolean verifyNodesColour() {
      return true; // already implemented
   }

   /**
    * The colour of the root must be black.
    */
   private boolean verifyRootColour() {
      return ((RBNode<T>) root).getColour() == Colour.BLACK; // already
      // implemented
   }

   /**
    * This is guaranteed by the constructor.
    */
   private boolean verifyNILNodeColour() {
      return true; // already implemented
   }

   /**
    * Verifies the property for all RED nodes: the children of a red node must
    * be BLACK.
    */
   private boolean verifyChildrenOfRedNodes() {
      return verifyChildrenOfRedNodesRecursive((RBNode<T>) this.root);
   }

   private boolean verifyChildrenOfRedNodesRecursive(RBNode<T> node) {
      boolean result = true;

      if (node != null && !node.isEmpty()) {
         if (node.getColour().equals(Colour.RED)) {
            if (!((RBNode<T>) node.getLeft()).getColour().equals(Colour.RED)
                  || !((RBNode<T>) node.getRight()).getColour().equals(Colour.RED)) {

               result = false;
            }
         }

         verifyChildrenOfRedNodesRecursive((RBNode<T>) node.getLeft());
         verifyChildrenOfRedNodesRecursive((RBNode<T>) node.getRight());

      }

      return result;
   }

   /**
    * Verifies the black-height property from the root.
    */
   private boolean verifyBlackHeight() {
      return this.blackHeightRecursive((RBNode<T>) this.root.getLeft()) == this
            .blackHeightRecursive((RBNode<T>) this.root.getRight());
   }

   @Override
   public void insert(T value) {
      if (value != null) {
         insertRecursive(value, (RBNode<T>) this.root);
      }
   }

   private void insertRecursive(T element, RBNode<T> node) {
      if (node.isEmpty()) {
         node.setData(element);
         node.setColour(Colour.RED);

         node.setLeft(new RBNode<>());
         node.getLeft().setParent(node);
         node.setRight(new RBNode<>());
         node.getRight().setParent(node);

         fixUpCase1(node);

      } else {
         if (element.compareTo(node.getData()) < 0) {
            insertRecursive(element, (RBNode<T>) node.getLeft());
         } else if (element.compareTo(node.getData()) > 0) {
            insertRecursive(element, (RBNode<T>) node.getRight());
         }
      }
   }

   @Override
   public RBNode<T>[] rbPreOrder() {
      List<RBNode<T>> list = new ArrayList<>();

      preOrder((RBNode<T>) this.root, list);
      RBNode<T>[] array = new RBNode[super.size()];

      return list.toArray(array);
   }

   protected void preOrder(RBNode<T> node, List<RBNode<T>> list) {
      if (!node.isEmpty()) {

         list.add(node);
         preOrder((RBNode<T>) node.getLeft(), list);
         preOrder((RBNode<T>) node.getRight(), list);
      }
   }

   // FIXUP methods
   protected void fixUpCase1(RBNode<T> node) {
      if (node != null) {
         if (node == this.root) {
            node.setColour(Colour.BLACK);
         } else {
            fixUpCase2(node);
         }
      }
   }

   protected void fixUpCase2(RBNode<T> node) {
      if (!((RBNode<T>) node.getParent()).getColour().equals(Colour.BLACK)) {
         fixUpCase3(node);
      }
   }

   protected void fixUpCase3(RBNode<T> node) {
      RBNode<T> parent = (RBNode<T>) node.getParent();
      RBNode<T> grandfather = (RBNode<T>) parent.getParent();
      RBNode<T> uncle;

      if (grandfather != null && !grandfather.isEmpty()) {
         if (grandfather.getRight().equals(parent)) {
            uncle = (RBNode<T>) grandfather.getLeft();
         } else {
            uncle = (RBNode<T>) grandfather.getRight();
         }

         if (uncle != null && (uncle.getColour().equals(Colour.RED))) {
            parent.setColour(Colour.BLACK);
            uncle.setColour(Colour.BLACK);
            grandfather.setColour(Colour.RED);
            fixUpCase1(grandfather);
         } else {
            fixUpCase4(node);
         }
      }
   }

   protected void fixUpCase4(RBNode<T> node) {
      RBNode<T> parent = (RBNode<T>) node.getParent();
      RBNode<T> grandfather = (RBNode<T>) parent.getParent();
      RBNode<T> next = node;

      if (parent.getRight().equals(next) && grandfather.getLeft().equals(parent)) {
         Util.leftRotation(parent);
         next = (RBNode<T>) next.getLeft();
      } else if (parent.getLeft().equals(next) && grandfather.getRight().equals(parent)) {
         Util.rightRotation(parent);
         next = (RBNode<T>) next.getRight();
      }

      fixUpCase5(next);
   }

   protected void fixUpCase5(RBNode<T> node) {
      RBNode<T> parent = (RBNode<T>) node.getParent();
      RBNode<T> grandfather = (RBNode<T>) parent.getParent();

      parent.setColour(Colour.BLACK);
      grandfather.setColour(Colour.RED);

      RBNode<T> aux;

      if (parent.getLeft().equals(node)) {
         aux = (RBNode<T>) Util.rightRotation(grandfather);
      } else {
         aux = (RBNode<T>) Util.leftRotation(grandfather);
      }

      if (aux.getParent() == null) {
         this.root = aux;
      }
   }
}
