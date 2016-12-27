public Boolean isLiked(String message_id) 
			{
				
					SQLiteDatabase db = getReadableDatabase();
					Cursor r =  db.query(true, Datas.TABLENAME_like, new String[] 
							{
								 Datas.TABLE_my_message_id
							},   Datas.TABLE_my_message_id + " LIKE ?",
				            new String[] { message_id }, null, null,null,
				            null);
					
					if(r.getCount() > 0)
					{
						return true;
					}
					else
					{
						return false;
					}
					
			}
