package com.advancia.models;

public class SlottedMemo {
    private SlotNumber slotNumber;
    private Memo memoContent;

    public SlottedMemo(SlotNumber slotNumber, Memo memoContent) {
        this.slotNumber = slotNumber;
        this.memoContent = memoContent;
    }

	public SlotNumber getSlotNumber() {
		return slotNumber;
	}

	public void setSlotNumber(SlotNumber slotNumber) {
		this.slotNumber = slotNumber;
	}

	public Memo getMemoContent() {
		return memoContent;
	}

	public void setMemoContent(Memo memoContent) {
		this.memoContent = memoContent;
	}
	
	public String toString() {
		  return "SlottedMemo [slotNumber=" + slotNumber + ", memoContent=" + memoContent + "]";
		}

	
}
