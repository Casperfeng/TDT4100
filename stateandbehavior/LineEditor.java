package stateandbehavior;

	public class LineEditor { 
		
		private StringBuilder text;
		private int insertionIndex;
		
		public LineEditor(String text) {
			this.text = new StringBuilder(text);
			this.insertionIndex = 0;
		}
		
		public LineEditor() {
			this.text=new StringBuilder("");
			this.insertionIndex = 0;
		}
		
		public LineEditor(String text, int insertionIndex) {
			this.text = new StringBuilder(text);
			if (insertionIndex<text.length()) {
				this.insertionIndex = insertionIndex;
			}
			else {
				System.out.println("ERROR: The given index is out of bounds");
			}
		}
		
		public void left() {
			if (this.insertionIndex!=0) {
				this.insertionIndex-=1;
			}
		}
		
		public void right() {
			if (this.insertionIndex<this.text.length()) {
				this.insertionIndex+=1;
			}
		}
		
		public void insertString(String text) {
			this.text.insert(this.insertionIndex, text);
			this.insertionIndex += text.length();
		}
		
		public void deleteLeft() {
			if (this.insertionIndex!=0) {
					this.text.deleteCharAt(this.insertionIndex-1);
					this.insertionIndex--;
			}
		}
		

		public void deleteRight() {
			int index = this.text.length();
			if (this.insertionIndex < index) {
				this.text.deleteCharAt(this.insertionIndex);
			}
		}
		
		public String getText() {
			return this.text.toString();
		}
		
		public void setText(String text) {
			this.text = new StringBuilder(text);
		}
		
		public void setInsertionIndex(int index) {
			if (index>=0) {
				this.insertionIndex = index;
			}
		}
		
		public int getInsertionIndex() {
			return this.insertionIndex;
		}
		
		public String toString() {
			return this.text.toString();
		}
		
	}