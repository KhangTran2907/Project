/* This program contains 2 parts: (1) and (2)
   YOUR TASK IS TO COMPLETE THE PART  (2)  ONLY
 */
//(1)==============================================================
import java.io.*;
import java.util.*;

class BSTree {

    Node root;

    BSTree() {
        root = null;
    }

    boolean isEmpty() {
        return (root == null);
    }

    void clear() {
        root = null;
    }

    void fvisit(Node p, RandomAccessFile f) throws Exception {
        //System.out.println(p);
        if (p != null) {
            f.writeBytes(p.info + " ");
        }
    }

    void preOrder(Node p, RandomAccessFile f) throws Exception {
        if (p == null) {
            return;
        }
        fvisit(p, f);
        preOrder(p.left, f);
        preOrder(p.right, f);
    }

    void inOrder(Node p, RandomAccessFile f) throws Exception {
        if (p == null) {
            return;
        }
        inOrder(p.left, f);
        fvisit(p, f);
        inOrder(p.right, f);
    }

    void postOrder(Node p, RandomAccessFile f) throws Exception {
        if (p == null) {
            return;
        }
        postOrder(p.left, f);
        postOrder(p.right, f);
        fvisit(p, f);
    }

    void breadth(Node p, RandomAccessFile f) throws Exception {
        if (p == null) {
            return;
        }
        Queue q = new Queue();
        q.enqueue(p);
        Node r;
        while (!q.isEmpty()) {
            r = q.dequeue();
            fvisit(r, f);
            if (r.left != null) {
                q.enqueue(r.left);
            }
            if (r.right != null) {
                q.enqueue(r.right);
            }
        }
    }

    void loadData(int k) //do not edit this function
    {
        String[] a = Lib.readLineToStrArray("data.txt", k);
        int[] b = Lib.readLineToIntArray("data.txt", k + 1);
        int[] c = Lib.readLineToIntArray("data.txt", k + 2);
        int n = a.length;
        for (int i = 0; i < n; i++) {
            insert(a[i], b[i], c[i]);
        }
    }

//===========================================================================
//(2)===YOU CAN EDIT OR EVEN ADD NEW FUNCTIONS IN THE FOLLOWING PART========
//===========================================================================
    void insert(String xName, int xAge, int xId) {
        //You should insert here statements to complete this function

        if (xAge <= 0) {
            return;
        }

        User x = new User(xName, xAge, xId);
        Node newNode = new Node(x);

        if (root == null) {
            root = newNode;
            return;
        }

        Node parent = null, current = root;
        while (current != null) {
            parent = current;
            if (xId == current.info.id) {
                return; // Duplicate id, do nothing
            }
            if (xId < current.info.id) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        if (xId < parent.info.id) {
            parent.left = newNode;
        } else {
            parent.right = newNode;
        }

    }

    void f1() throws Exception {/* You do not need to edit this function. Your task is to complete insert  function
        above only.
         */
        clear();
        loadData(1);
        String fname = "f1.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        breadth(root, f);
        f.writeBytes("\r\n");
        inOrder(root, f);
        f.writeBytes("\r\n");
        f.close();
    }

//=============================================================
    void f2() throws Exception {
        clear();
        loadData(5);
        String fname = "f2.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        preOrder(root, f);
        f.writeBytes("\r\n");
        //------------------------------------------------------------------------------------
        /*You must keep statements pre-given in this function.
        Your task is to insert statements here, just after this comment,
        to complete the question in the exam paper.*/
        postOrderAgeLessThan(root, f, 25);
        //------------------------------------------------------------------------------------
        f.writeBytes("\r\n");
        f.close();
    }
        void postOrderAgeLessThan(Node p, RandomAccessFile f, int age) throws Exception {
    if (p == null) return;
    postOrderAgeLessThan(p.left, f, age);
    postOrderAgeLessThan(p.right, f, age);
    if (p.info.age < age) fvisit(p, f);
}

// f.writeBytes(" k = " + k + "\r\n");
//=============================================================
    void f3() throws Exception {
        clear();
        loadData(9);
        String fname = "f3.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        breadth(root, f);
        f.writeBytes("\r\n");
        //------------------------------------------------------------------------------------
        /*You must keep statements pre-given in this function.
       Your task is to insert statements here, just after this comment,
       to complete the question in the exam paper.*/
        updateAgeForOneChildNodes(root);
        //------------------------------------------------------------------------------------
        breadth(root, f);
        f.writeBytes("\r\n");
        f.close();
    }
        void updateAgeForOneChildNodes(Node p) {
    if (p == null) return;
    if ((p.left == null && p.right != null) || (p.left != null && p.right == null)) {
        p.info.age += 3;
    }
    updateAgeForOneChildNodes(p.left);
    updateAgeForOneChildNodes(p.right);
}
    

//=============================================================
    void f4() throws Exception {
        clear();
        loadData(13);
        String fname = "f4.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        preOrder(root, f);
        f.writeBytes("\r\n");
        
        //------------------------------------------------------------------------------------
        /*You must keep statements pre-given in this function.
        Your task is to insert statements here, just after this comment,
        to complete the question in the exam paper.*/
        
            Node[] lastNode = new Node[1]; // 
    getLastPostOrderNode(root, lastNode);
    int h = getHeight(root, lastNode[0].info.id);
        //------------------------------------------------------------------------------------
        f.writeBytes(h + "");
        f.writeBytes("\r\n");
        f.close();
    }
        void getLastPostOrderNode(Node p, Node[] result) {
    if (p == null) return;
    getLastPostOrderNode(p.left, result);
    getLastPostOrderNode(p.right, result);
    result[0] = p;
}

int getHeight(Node node, int targetId) {
    return getHeightHelper(node, targetId, 0);
}

int getHeightHelper(Node node, int targetId, int depth) {
    if (node == null) return -1;
    if (node.info.id == targetId) return depth;
    int left = getHeightHelper(node.left, targetId, depth + 1);
    int right = getHeightHelper(node.right, targetId, depth + 1);
    return Math.max(left, right);
}

//=============================================================    
    void f5() throws Exception {
        clear();
        loadData(17);
        String fname = "f5.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        inOrder(root, f);
        f.writeBytes("\r\n");

        //------------------------------------------------------------------------------------
        /*You must keep statements pre-given in this function.
        Your task is to insert statements here, just after this comment,
        to complete the question in the exam paper.*/
        if (root != null && root.left != null) {
        resetAge(root.left);
    }
        //------------------------------------------------------------------------------------
        inOrder(root, f);
        f.writeBytes("\r\n");
        f.close();
    }
        void resetAge(Node p) {
    if (p == null) return;
    p.info.age = 0;
    resetAge(p.left);
    resetAge(p.right);
}

//=============================================================
    void f6() throws Exception {
        clear();
        loadData(21);
        String fname = "f6.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        inOrder(root, f);
        f.writeBytes("\r\n");
        Node right_most = null;//right_most node
        //------------------------------------------------------------------------------------
        /*You must keep statements pre-given in this function.
        Your task is to insert statements here, just after this comment,
        to complete the question in the exam paper.*/
            while (right_most.right != null) {
        right_most = right_most.right;
    }
        //------------------------------------------------------------------------------------
        fvisit(right_most, f);
        f.writeBytes("\r\n");
        f.close();
    }
        

    //=============================================================
    void f7() throws Exception {
        clear();
        loadData(25);
        String fname = "f7.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        inOrder(root, f);
        f.writeBytes("\r\n");

        //------------------------------------------------------------------------------------
        /*You must keep statements pre-given in this function.
        Your task is to insert statements here, just after this comment,
        to complete the question in the exam paper.*/
        root = deleteByCopy(root, root.info.id);
        //------------------------------------------------------------------------------------
        inOrder(root, f);
        f.writeBytes("\r\n");
        f.close();
    }
        Node deleteByCopy(Node p, int id) {
    if (p == null) return null;
    if (id < p.info.id) {
        p.left = deleteByCopy(p.left, id);
    } else if (id > p.info.id) {
        p.right = deleteByCopy(p.right, id);
    } else {
        if (p.left == null) return p.right;
        if (p.right == null) return p.left;
        Node q = p.left;
        Node parent = p;
        while (q.right != null) {
            parent = q;
            q = q.right;
        }
        p.info = q.info;
        if (parent == p) {
            parent.left = q.left;
        } else {
            parent.right = q.left;
        }
    }
    return p;
}

    //=============================================================
    void f8() throws Exception {
        clear();
        loadData(29);
        String fname = "f8.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        inOrder(root, f);
        f.writeBytes("\r\n");
        
        //------------------------------------------------------------------------------------
        /*You must keep statements pre-given in this function.
        Your task is to insert statements here, just after this comment,
        to complete the question in the exam paper.*/
        int k = countImbalancedNodes(root);
        //------------------------------------------------------------------------------------
        f.writeBytes(k + "\r\n");
        f.close();
    }
    int countImbalancedNodes(Node p) {
    if (p == null) return 0;
    int lh = height(p.left);
    int rh = height(p.right);
    int count = (Math.abs(lh - rh) > 1) ? 1 : 0;
    return count + countImbalancedNodes(p.left) + countImbalancedNodes(p.right);
}

int height(Node p) {
    if (p == null) return 0;
    return 1 + Math.max(height(p.left), height(p.right));
}
}
