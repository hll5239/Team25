package com.myin.team25.domain;

public class CommentCriteria { //�������� �ʿ��� ��ŭ ����ϱ� ���� Ŭ����

	private int block;	
	private int perBlockNum;
	
	public CommentCriteria(){	//page��ȣ 1���� ���� perPageNum ����� �������� ����
		this.block = 1;
		this.perBlockNum=10;
	}
	
	public void setBlock(int block){	//page�� 1���� �۰ų� ������� 1�� �־��ش�.
		if(block<=1){
			this.block =1;
			return;
		}
		this.block=block;
	}
	
	public void setPerBlockNum(int perBlockNum){	//0���� �۰ų����ų� 100���� Ŭ��� �����������ٰ����� 15���� �Ѵ�.
		if(perBlockNum <=0 || perBlockNum >100){
			this.perBlockNum=10;
			return;
		}
		this.perBlockNum=perBlockNum;
	}
	
	public int getBlock(){
		return block;
	}
	
	public int getPerBlockNum(){
		return perBlockNum;
	}
	
	public int getNextBlock(){	//������������ġ 
		int block =0;
		
		block=this.block+1;
		
		return block;
	}

	
	@Override
	public String toString(){
		return "CommentCriteria [block=" + block+", " + "perBlockNum="+perBlockNum+ "]";
	}
	
}
