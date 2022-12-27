package com.example.doome.dungeon_lords;

public class DialogueNode {
    /**
     * response that causes this node to activate. Set to the empty String if this node is a leaf in
     * the dialogue tree
     */
    private String linkedResp = "";
    private String bit = "";
    private DialogueNode left = null;
    private DialogueNode right = null;

    public DialogueNode(String myLinkedResp, String myBit) {
        linkedResp = myLinkedResp;
        bit = myBit;
    }

    public DialogueNode DNAppendLeft(DialogueNode newLeft) {
        left = newLeft;
        return newLeft;
    }

    public DialogueNode DNAppendRight(DialogueNode newRight) {
        right = newRight;
        return newRight;
    }

    //here-document
    
    public void appendLeaves() {
        this.DNAppendLeft(new DialogueNode("Goodbye.", ""));
        this.DNAppendRight(new DialogueNode("Goodbye.", ""));
    }

    public String getLeftResponse() {
        return left.linkedResp;
    }

    public String getRightResponse() {
        return right.linkedResp;
    }

    public String getBit() {
        return bit;
    }

    public DialogueNode getLeft() {
        return left;
    }

    public DialogueNode getRight() {
        return right;
    }
}
