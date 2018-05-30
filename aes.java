import java.util.Arrays;

public class aes {
	
	private static short[] sbox = {
		  //0     1    2      3     4    5     6     7      8    9     A      B    C     D     E     F
		  0x63, 0x7c, 0x77, 0x7b, 0xf2, 0x6b, 0x6f, 0xc5, 0x30, 0x01, 0x67, 0x2b, 0xfe, 0xd7, 0xab, 0x76,
		  0xca, 0x82, 0xc9, 0x7d, 0xfa, 0x59, 0x47, 0xf0, 0xad, 0xd4, 0xa2, 0xaf, 0x9c, 0xa4, 0x72, 0xc0,
		  0xb7, 0xfd, 0x93, 0x26, 0x36, 0x3f, 0xf7, 0xcc, 0x34, 0xa5, 0xe5, 0xf1, 0x71, 0xd8, 0x31, 0x15,
		  0x04, 0xc7, 0x23, 0xc3, 0x18, 0x96, 0x05, 0x9a, 0x07, 0x12, 0x80, 0xe2, 0xeb, 0x27, 0xb2, 0x75,
		  0x09, 0x83, 0x2c, 0x1a, 0x1b, 0x6e, 0x5a, 0xa0, 0x52, 0x3b, 0xd6, 0xb3, 0x29, 0xe3, 0x2f, 0x84,
		  0x53, 0xd1, 0x00, 0xed, 0x20, 0xfc, 0xb1, 0x5b, 0x6a, 0xcb, 0xbe, 0x39, 0x4a, 0x4c, 0x58, 0xcf,
		  0xd0, 0xef, 0xaa, 0xfb, 0x43, 0x4d, 0x33, 0x85, 0x45, 0xf9, 0x02, 0x7f, 0x50, 0x3c, 0x9f, 0xa8,
		  0x51, 0xa3, 0x40, 0x8f, 0x92, 0x9d, 0x38, 0xf5, 0xbc, 0xb6, 0xda, 0x21, 0x10, 0xff, 0xf3, 0xd2,
		  0xcd, 0x0c, 0x13, 0xec, 0x5f, 0x97, 0x44, 0x17, 0xc4, 0xa7, 0x7e, 0x3d, 0x64, 0x5d, 0x19, 0x73,
		  0x60, 0x81, 0x4f, 0xdc, 0x22, 0x2a, 0x90, 0x88, 0x46, 0xee, 0xb8, 0x14, 0xde, 0x5e, 0x0b, 0xdb,
		  0xe0, 0x32, 0x3a, 0x0a, 0x49, 0x06, 0x24, 0x5c, 0xc2, 0xd3, 0xac, 0x62, 0x91, 0x95, 0xe4, 0x79,
		  0xe7, 0xc8, 0x37, 0x6d, 0x8d, 0xd5, 0x4e, 0xa9, 0x6c, 0x56, 0xf4, 0xea, 0x65, 0x7a, 0xae, 0x08,
		  0xba, 0x78, 0x25, 0x2e, 0x1c, 0xa6, 0xb4, 0xc6, 0xe8, 0xdd, 0x74, 0x1f, 0x4b, 0xbd, 0x8b, 0x8a,
		  0x70, 0x3e, 0xb5, 0x66, 0x48, 0x03, 0xf6, 0x0e, 0x61, 0x35, 0x57, 0xb9, 0x86, 0xc1, 0x1d, 0x9e,
		  0xe1, 0xf8, 0x98, 0x11, 0x69, 0xd9, 0x8e, 0x94, 0x9b, 0x1e, 0x87, 0xe9, 0xce, 0x55, 0x28, 0xdf,
		  0x8c, 0xa1, 0x89, 0x0d, 0xbf, 0xe6, 0x42, 0x68, 0x41, 0x99, 0x2d, 0x0f, 0xb0, 0x54, 0xbb, 0x16 
	};
	
	private static short[] rsbox = {
		  0x52, 0x09, 0x6a, 0xd5, 0x30, 0x36, 0xa5, 0x38, 0xbf, 0x40, 0xa3, 0x9e, 0x81, 0xf3, 0xd7, 0xfb,
		  0x7c, 0xe3, 0x39, 0x82, 0x9b, 0x2f, 0xff, 0x87, 0x34, 0x8e, 0x43, 0x44, 0xc4, 0xde, 0xe9, 0xcb,
		  0x54, 0x7b, 0x94, 0x32, 0xa6, 0xc2, 0x23, 0x3d, 0xee, 0x4c, 0x95, 0x0b, 0x42, 0xfa, 0xc3, 0x4e,
		  0x08, 0x2e, 0xa1, 0x66, 0x28, 0xd9, 0x24, 0xb2, 0x76, 0x5b, 0xa2, 0x49, 0x6d, 0x8b, 0xd1, 0x25,
		  0x72, 0xf8, 0xf6, 0x64, 0x86, 0x68, 0x98, 0x16, 0xd4, 0xa4, 0x5c, 0xcc, 0x5d, 0x65, 0xb6, 0x92,
		  0x6c, 0x70, 0x48, 0x50, 0xfd, 0xed, 0xb9, 0xda, 0x5e, 0x15, 0x46, 0x57, 0xa7, 0x8d, 0x9d, 0x84,
		  0x90, 0xd8, 0xab, 0x00, 0x8c, 0xbc, 0xd3, 0x0a, 0xf7, 0xe4, 0x58, 0x05, 0xb8, 0xb3, 0x45, 0x06,
		  0xd0, 0x2c, 0x1e, 0x8f, 0xca, 0x3f, 0x0f, 0x02, 0xc1, 0xaf, 0xbd, 0x03, 0x01, 0x13, 0x8a, 0x6b,
		  0x3a, 0x91, 0x11, 0x41, 0x4f, 0x67, 0xdc, 0xea, 0x97, 0xf2, 0xcf, 0xce, 0xf0, 0xb4, 0xe6, 0x73,
		  0x96, 0xac, 0x74, 0x22, 0xe7, 0xad, 0x35, 0x85, 0xe2, 0xf9, 0x37, 0xe8, 0x1c, 0x75, 0xdf, 0x6e,
		  0x47, 0xf1, 0x1a, 0x71, 0x1d, 0x29, 0xc5, 0x89, 0x6f, 0xb7, 0x62, 0x0e, 0xaa, 0x18, 0xbe, 0x1b,
		  0xfc, 0x56, 0x3e, 0x4b, 0xc6, 0xd2, 0x79, 0x20, 0x9a, 0xdb, 0xc0, 0xfe, 0x78, 0xcd, 0x5a, 0xf4,
		  0x1f, 0xdd, 0xa8, 0x33, 0x88, 0x07, 0xc7, 0x31, 0xb1, 0x12, 0x10, 0x59, 0x27, 0x80, 0xec, 0x5f,
		  0x60, 0x51, 0x7f, 0xa9, 0x19, 0xb5, 0x4a, 0x0d, 0x2d, 0xe5, 0x7a, 0x9f, 0x93, 0xc9, 0x9c, 0xef,
		  0xa0, 0xe0, 0x3b, 0x4d, 0xae, 0x2a, 0xf5, 0xb0, 0xc8, 0xeb, 0xbb, 0x3c, 0x83, 0x53, 0x99, 0x61,
		  0x17, 0x2b, 0x04, 0x7e, 0xba, 0x77, 0xd6, 0x26, 0xe1, 0x69, 0x14, 0x63, 0x55, 0x21, 0x0c, 0x7d 
	};
	
	static short[] Rcon = {
		0x8d, 0x01, 0x02, 0x04, 0x08, 0x10, 0x20, 0x40, 0x80, 0x1b, 0x36 };
	
	final short Nk = 4,    //number of 32 bit words in a key
				Nr = 10,   //number of rounds in AES cipher function
				Nb = 4;    //number of columns in a state in AES
	
	private short[] 	RoundKey;
	private short[][] 	state;
	private short[] 	Iv;
	
	public aes()
	{
		this.RoundKey = new short[176];
		this.state = new short[4][4];
	}
	
	private void KeyExpansion(short[] Key) 
	{
		short i, j, k;
		short[] temp = new short[4];
		
		//initial the first round key
		for(i = 0; i < Nk; i++) 
		{
			RoundKey[(i * 4) + 0] = Key[(i * 4) + 0];
		    RoundKey[(i * 4) + 1] = Key[(i * 4) + 1];
		    RoundKey[(i * 4) + 2] = Key[(i * 4) + 2];
		    RoundKey[(i * 4) + 3] = Key[(i * 4) + 3];
		}
		
		for(i = Nk; i < Nb * (Nr + 1); i++)
		{
			k = (short) ((i - 1) * 4);
			temp[0]=RoundKey[k + 0];
			temp[1]=RoundKey[k + 1];
			temp[2]=RoundKey[k + 2];
			temp[3]=RoundKey[k + 3];
			
			if(i % Nk == 0)
			{
				
				// shift words to the left, [a0,a1,a2,a3] becomes [a1,a2,a3,a0]
				k = temp[0];
				temp[0] = temp[1];
				temp[1] = temp[2];
				temp[2] = temp[3];
				temp[3] = k;
				
			    // replacing the words with sbox
				temp[0] = sbox[temp[0]];
				temp[1] = sbox[temp[1]];
				temp[2] = sbox[temp[2]];
				temp[3] = sbox[temp[3]];
				
				temp[0] = (short) (temp[0] ^ Rcon[i/Nk]);
			}
			
			j = (short) (i * 4); k=(short) ((i - Nk) * 4);
		    RoundKey[j + 0] = (short) (RoundKey[k + 0] ^ temp[0]);
		    RoundKey[j + 1] = (short) (RoundKey[k + 1] ^ temp[1]);
		    RoundKey[j + 2] = (short) (RoundKey[k + 2] ^ temp[2]);
		    RoundKey[j + 3] = (short) (RoundKey[k + 3] ^ temp[3]);
		}
	}
		
	private void AddRoundKey(short round, short[][] state)
	{
		for(short i = 0; i < 4; i++) 
		{
			for(short j = 0; j < 4; j++)
			{
				state[i][j] ^= RoundKey[(round * Nb * 4) + (i * Nb) + j];
			}
		}
	}
	
	private void SubBytes(short[][] state)
	{
		for(short i = 0; i < 4; i++) 
		{
			for(short j = 0; j < 4; j++)
			{
				state[j][i] = sbox[state[j][i]];
			}
		}
	}
	
	private void ShiftRows(short[][] state)
	{
		short temp;
		
		// Rotate first row 1 columns to left
		temp        = state[0][1];
		state[0][1] = state[1][1];
		state[1][1] = state[2][1];
		state[2][1] = state[3][1];
		state[3][1] = temp;

		// Rotate second row 2 columns to left  
		temp        = state[0][2];
		state[0][2] = state[2][2];
		state[2][2] = temp;

		temp        = state[1][2];
		state[1][2] = state[3][2];
		state[3][2] = temp;

		// Rotate third row 3 columns to left
		temp        = state[0][3];
		state[0][3] = state[3][3];
		state[3][3] = state[2][3];
		state[2][3] = state[1][3];
		state[1][3] = temp;
	}
	
	private short xtime(short x)
	{
		return (short) ((short) ((x<<1) ^ (((x>>7) & 1) * 0x1b)) & 0x00ff);
	}
	
	private void MixColumns(short[][] state)
	{
		short Tmp, Tm, t;
		for(short i = 0; i < 4; i++)
		{
			t 	= state[i][0];
			Tmp = (short) (state[i][0] ^ state[i][1] ^ state[i][2] ^ state[i][3]);
			Tm	= (short) (state[i][0] ^ state[i][1]); Tm = xtime(Tm);  state[i][0] ^= Tm ^ Tmp;
		    Tm  = (short) (state[i][1] ^ state[i][2]); Tm = xtime(Tm);  state[i][1] ^= Tm ^ Tmp;
		    Tm  = (short) (state[i][2] ^ state[i][3]); Tm = xtime(Tm);  state[i][2] ^= Tm ^ Tmp;
		    Tm  = (short) (state[i][3] ^ t);           Tm = xtime(Tm);  state[i][3] ^= Tm ^ Tmp;
		}
	}
	
	private void InvShiftRows()
	{
		short temp;
		
		temp = state[3][1];
		state[3][1] = state[2][1];
		state[2][1] = state[1][1];
		state[1][1] = state[0][1];
		state[0][1] = temp;
		
		// Rotate second row 2 columns to right 
		temp = state[0][2];
		state[0][2] = state[2][2];
		state[2][2] = temp;
		
		temp = state[1][2];
		state[1][2] = state[3][2];
		state[3][2] = temp;
		
		// Rotate third row 3 columns to right
		temp = state[0][3];
		state[0][3] = state[1][3];
		state[1][3] = state[2][3];
		state[2][3] = state[3][3];
		state[3][3] = temp;
	}
	
	private void InvSubBytes()
	{
		for(short i = 0; i < 4; i++)
		{
			for(short j = 0; j < 4; j++)
			{
				state[j][i] = rsbox[state[j][i]];
			}
		}
	}
	
	private short Multiply(short x, int i)
	{
		return (short) (((i & 1) * x) ^
			       ((i>>1 & 1) * xtime(x)) ^
			       ((i>>2 & 1) * xtime(xtime(x))) ^
			       ((i>>3 & 1) * xtime(xtime(xtime(x)))) ^
			       ((i>>4 & 1) * xtime(xtime(xtime(xtime(x))))) & 0x00ff);
	}
	
	private void InvMixColumns()
	{
		short a, b, c, d;
		for(short i = 0; i < 4; i++)
		{
			a = state[i][0];
		    b = state[i][1];
		    c = state[i][2];
		    d = state[i][3];

		    state[i][0] = (short) (Multiply(a, 0x0e) ^ Multiply(b, 0x0b) ^ Multiply(c, 0x0d) ^ Multiply(d, 0x09));
		    state[i][1] = (short) (Multiply(a, 0x09) ^ Multiply(b, 0x0e) ^ Multiply(c, 0x0b) ^ Multiply(d, 0x0d));
		    state[i][2] = (short) (Multiply(a, 0x0d) ^ Multiply(b, 0x09) ^ Multiply(c, 0x0e) ^ Multiply(d, 0x0b));
		    state[i][3] = (short) (Multiply(a, 0x0b) ^ Multiply(b, 0x0d) ^ Multiply(c, 0x09) ^ Multiply(d, 0x0e));

		}
	}
	
	private void Cipher(short[][] state)
	{
		short round = 0;
		
		AddRoundKey(round, state);
		//this.printState();
		
		for(round = 1; round < Nr; round++)
		{
			SubBytes(state);
			//if(round == 1) this.printState();
			ShiftRows(state);
			//if(round == 1) this.printState();
			MixColumns(state);
			//if(round == 1) this.printState();
			AddRoundKey(round, state);
		}
		
		//last round
		SubBytes(state);
		ShiftRows(state);
		AddRoundKey(round, state);
		
		//this.printState();
	}
	
	private void InvCipher(short[][] state)
	{
		short round = 0;
		
		// Add the First round key to the state before starting the rounds.
		AddRoundKey(Nr, state);
		
		// There will be Nr rounds.
		// The first Nr-1 rounds are identical.
		// These Nr-1 rounds are executed in the loop below.
		for(round = (Nr - 1); round > 0; round--)
		{
			InvShiftRows();
			InvSubBytes();
			AddRoundKey(round, state);
			InvMixColumns();
		}
		
		// The last round.
		// The MixColumns function is not in the last round.
		InvShiftRows();
		InvSubBytes();
		AddRoundKey((short)0, state);
	}
	
	private void IniState(short[] in)
	{	
		short j = 0;
		for(short i = 0; i < in.length; i++)
		{
			if(i != 0 &&i % 4 == 0)
				j++;
			this.state[j][i%4] = in[i];
		}
	}
	
	private void printKey(short[] Key) 
	{
		System.out.println(Arrays.toString(Key));
	}
	
	private void printState()
	{
		for(short i=0; i<4; i++)
		{
			for(short j=0; j<4; j++)
			{
				System.out.print(Integer.toHexString(state[j][i])+" ");
			}
			System.out.println("");
		}
		System.out.println();
	}
	
	private short[] StateToArray()
	{
		short[] out = new short[16];
		short k = 0;
		for(short i = 0; i < 4; i++)
		{
			for(short j = 0; j < 4; j++)
			{
				out[k++] = this.state[i][j];
			}
		}
		return out;
	}
	
	public void AES_init(short[] Key)
	{
		KeyExpansion(Key);
	}
	
	public short[] AES_ECB_encrypt(short[] in)
	{
		this.IniState(in);
		this.Cipher(this.state);
		return this.StateToArray();
	}
	
	public short[] AES_ECB_decrypt(short[] in)
	{
		this.IniState(in);
		this.InvCipher(this.state);
		return this.StateToArray();
	}
	
	
	//CBC encryption
	public void AES_init(short[] Key, short[] Iv)
	{
		this.Iv = new short[16];
		changeIn(this.Iv, Iv, 0);
		KeyExpansion(Key);
	}
	
	private short[] XorIv(short[] in, int p)
	{
		short temp[] = new short[16];
		for(int i = 0; i < 16; i++)
		{
			temp[i] = (short) (in[p+i] ^ Iv[i]);
		}
		return temp;
	}
	
	private void changeIn(short[] in, short[] temp, int p)
	{
		for(int i = 0; i < temp.length; i++)
		{
			in[p+i] = temp[i];
		}
	}
	
	private short[] CutIn(short[] in, int p)
	{
		short[] temp = new short[16];
		for(int i = 0; i < 16; i++)
		{
			temp[i] = in[i+p];
		}
		return temp;
	}
	
	public short[] AES_CBC_encrypt(short[] in)
	{
		short[] out = new short[in.length];
		for(int i = 0; i < in.length; i += 16)
		{
			this.IniState(XorIv(in, i));
			this.Cipher(this.state);
			this.Iv = this.StateToArray();
			changeIn(out, this.Iv, i);
		}
		return out;
	}
	
	public short[] AES_CBC_decrypt(short[] in)
	{
		short[] out = new short[in.length];
		for(int i = 0; i < in.length; i += 16)
		{
			this.IniState(CutIn(in, i));
			this.InvCipher(this.state);
			changeIn(out, XorIv(this.StateToArray(), 0), i);
			this.Iv = CutIn(in, i);
		}
		return out;
	}
	
	public short[] AES_CTR(short[] in)
	{
		short[] out = new short[in.length];
		short[] temp = new short[16];
		for(int i = 0, p = 16; i < in.length; i++, p++)
		{
			if(p == 16)
			{
				IniState(this.Iv);
				Cipher(this.state);
				changeIn(temp, this.StateToArray(), 0);
				
				for(p = 15; p >= 0; p--)
				{
					if(this.Iv[p] == 255)
					{
						this.Iv[p] = 0;
						continue;
					}
					this.Iv[p] += 1;
					break;
				}
				
				p = 0;
			}
			out[i] = (short) (in[i] ^ temp[p]);
		}
		
		return out;
	}
}
