package com.asafov.naum;


public class BinarySearchTree implements SimpleBinaryTree{

    Node root;


    private static class Node{
        int value;
        Node left;
        Node right;
        Node(int value){
            this.value = value;
            left = null;
            right = null;
        }
    }

    private Node addElement(Node current,int value){
        if (current == null){
            return new Node(value);
        }
        if (value < current.value){
            current.left = addElement(current.left,value);
        }else if (value > current.value){
            current.right = addElement(current.right,value);
        }else {
            //value exists
            return current;
        }
        return current;
    }



    @Override
    public void add(int value) {
        root = addElement(root,value);
    }

    @Override
    public boolean contains(int value) {
        return contains(root,value);
    }


    private boolean contains(Node node,int value){
        if (node == null){
            return false;
        }
        if (node.value == value){
            return true;
        }
        if (value > node.value){
            return contains(node.right,value);
        }
        if (value < node.value){
            return contains(node.left,value);
        }
        return false;
    }

    @Override
    public void remove(int value){
        root = removeElement(root,value);
    }
    private Node removeElement(Node node,int value){
        if (node == null){
            return null;
        }

        if (node.value == value){
            //Remove element
            if (node.left == null && node.right == null){
                return null;
            }
            if (node.right == null){
                return node.left;
            }
            if (node.left == null){
                return node.right;
            }
            int smallest = smallestValue(node.right);
            node.value = smallest;
            node.right = removeElement(node.right,smallest);
            return node;
        }

        if (value > node.value){
            node.right = removeElement(node.right,value);
            return node;
        }
        if (value < node.value){
            node.left = removeElement(node.left,value);
            return node;
        }

        return node;
    }

    private int smallestValue(Node node){
        if (node.left == null){
            return node.value;
        }else {
            return smallestValue(node.left);
        }
    }

    @Override
    public String toString() {
        return getString(root);
    }

    private String getString(Node node){
        StringBuilder str = new StringBuilder();
        if (node == null){
            return "";
        }else {
            str.append(node.value).append("\n");
        }
        str.append(getString(node.left));
        str.append(getString(node.right));
        return new String(str);
    }
}
