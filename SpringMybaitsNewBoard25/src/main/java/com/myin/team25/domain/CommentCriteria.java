package com.myin.team25.domain;

public class CommentCriteria { //쿼리에서 필요한 만큼 출력하기 위한 클래스

	private int block;	
	private int perBlockNum;
	
	public CommentCriteria(){	//page번호 1부터 시작 perPageNum 몇개까지 보여줄지 지정
		this.block = 1;
		this.perBlockNum=10;
	}
	
	public void setBlock(int block){	//page가 1보다 작거나 같을경우 1을 넣어준다.
		if(block<=1){
			this.block =1;
			return;
		}
		this.block=block;
	}
	
	public void setPerBlockNum(int perBlockNum){	//0보다 작거나같거나 100보다 클경우 페이지보여줄개수를 15개로 한다.
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
	
	public int getNextBlock(){	//페이지시작위치 
		int block =0;
		
		block=this.block+1;
		
		return block;
	}

	
	@Override
	public String toString(){
		return "CommentCriteria [block=" + block+", " + "perBlockNum="+perBlockNum+ "]";
	}
	
}
