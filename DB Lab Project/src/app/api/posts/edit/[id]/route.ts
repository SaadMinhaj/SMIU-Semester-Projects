import { NextResponse } from 'next/server';
import { db } from '@/lib/db';
import { getSession } from '@/lib/auth';

export async function PUT(req: Request, { params }: { params: { id: string } }) {
  const { title, content } = await req.json();
  const session = getSession();

  if (!session) {
    return NextResponse.json({ message: 'Unauthorized' }, { status: 401 });
  }

  const [post] = await db.execute('SELECT * FROM posts WHERE id = ? AND user_id = ?', [params.id, session.id]);
  if (post.length === 0) {
    return NextResponse.json({ message: 'Post not found or unauthorized' }, { status: 404 });
  }

  await db.execute('UPDATE posts SET title = ?, content = ? WHERE id = ?', [title, content, params.id]);

  return NextResponse.json({ message: 'Post updated successfully' });
}   