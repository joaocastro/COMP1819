package Generation;

import java.util.HashMap;

public class StackController {
  private static HashMap<Instructions, Integer> stackCosts;

  static {
    stackCosts = new HashMap<Instructions, Integer>();

    stackCosts.put(Instructions.ILOAD, 1);
    stackCosts.put(Instructions.ALOAD, 1);
    stackCosts.put(Instructions.ICONST, 1);
    stackCosts.put(Instructions.GETSTATIC, 1);
    stackCosts.put(Instructions.ISTORE, -1);
    stackCosts.put(Instructions.IASTORE, -3);
    stackCosts.put(Instructions.ASTORE, -1);
    stackCosts.put(Instructions.IALOAD, -1);
    stackCosts.put(Instructions.PUTSTATIC, -1);
    stackCosts.put(Instructions.BIPUSH, 1);
    stackCosts.put(Instructions.SIPUSH, 1);
    stackCosts.put(Instructions.LDC, 1);
    stackCosts.put(Instructions.INVOKESTATIC, 0);
    stackCosts.put(Instructions.INVOKEVIRTUAL, 0);
    stackCosts.put(Instructions.INVOKESPECIAL, 0);
    stackCosts.put(Instructions.ARRAYLENGTH, 0);
    stackCosts.put(Instructions.OP, -1);
    stackCosts.put(Instructions.DUP, 1);
    stackCosts.put(Instructions.NEW, 1);
    stackCosts.put(Instructions.NEWARRAY, 0);
    stackCosts.put(Instructions.ARETURN, -1);
    stackCosts.put(Instructions.IRETURN, -1);
    stackCosts.put(Instructions.RETURN, 0);
    stackCosts.put(Instructions.GETFIELD, 0);
    stackCosts.put(Instructions.PUTFIELD, -2);
  }

  private int maxStack;
  private int currentSize;

  public StackController() {
    this.maxStack = 0;
    this.currentSize = 0;
  }

  public void addInstruction(Instructions instruction, int args) {
    this.currentSize += stackCosts.get(instruction);
    this.currentSize -= args;

    if (this.currentSize < 0)
      this.currentSize = 0;

    if (this.currentSize > this.maxStack)
      this.maxStack = this.currentSize;
  }

  public void addInstruction(Instructions instruction, int args,
                             boolean returnsValue) {
    this.currentSize += stackCosts.get(instruction);
    this.currentSize -= args;

    if (returnsValue)
      this.currentSize++;

    if (this.currentSize < 0)
      this.currentSize = 0;

    if (this.currentSize > this.maxStack)
      this.maxStack = this.currentSize;
  }

  public int getMaxStack() { return this.maxStack; }
}
