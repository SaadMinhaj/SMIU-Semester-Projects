'use client';
import { useEffect, useState } from 'react';
import { useRouter } from 'next/navigation';

type Post = {
  id: number;
  title: string;
  content: string;
  createdAt?: string;
  updatedAt?: string;
};

export default function Dashboard() {
  const router = useRouter();
  const [posts, setPosts] = useState<Post[]>([]);
  const [title, setTitle] = useState('');
  const [content, setContent] = useState('');
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState('');
  const [isCreating, setIsCreating] = useState(false);
  const [deletingId, setDeletingId] = useState<number | null>(null);
  const [editingPostId, setEditingPostId] = useState<number | null>(null);
  const [editedTitle, setEditedTitle] = useState('');
  const [editedContent, setEditedContent] = useState('');
  const [updating, setUpdating] = useState(false);

  const fetchPosts = async () => {
    try {
      setLoading(true);
      const res = await fetch('/api/admin');
      if (!res.ok) throw new Error('Failed to fetch posts');
      const data = await res.json();
      setPosts(data);
    } catch (err) {
      setError(err instanceof Error ? err.message : 'An error occurred');
    } finally {
      setLoading(false);
    }
  };

  const createPost = async () => {
    if (!title.trim() || !content.trim()) {
      setError('Title and content are required');
      return;
    }

    try {
      setIsCreating(true);
      setError('');
      const res = await fetch('/api/posts/create', {
        method: 'POST',
        body: JSON.stringify({ title, content }),
        headers: { 'Content-Type': 'application/json' },
      });

      if (!res.ok) throw new Error('Failed to create post');

      setTitle('');
      setContent('');
      await fetchPosts();
    } catch (err) {
      setError(err instanceof Error ? err.message : 'Failed to create post');
    } finally {
      setIsCreating(false);
    }
  };

  const deletePost = async (id: number) => {
    try {
      setDeletingId(id);
      const res = await fetch(`/api/posts/delete/${id}`, { method: 'DELETE' });
      if (!res.ok) throw new Error('Failed to delete post');
      await fetchPosts();
    } catch (err) {
      setError(err instanceof Error ? err.message : 'Failed to delete post');
    } finally {
      setDeletingId(null);
    }
  };

  const handleEdit = (post: Post) => {
    setEditingPostId(post.id);
    setEditedTitle(post.title);
    setEditedContent(post.content);
  };

  const cancelEdit = () => {
    setEditingPostId(null);
    setEditedTitle('');
    setEditedContent('');
  };

  const updatePost = async (id: number) => {
    if (!editedTitle.trim() || !editedContent.trim()) {
      setError('Both fields are required.');
      return;
    }

    try {
      setUpdating(true);
      const res = await fetch(`/api/posts/edit/${id}`, {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ title: editedTitle, content: editedContent }),
      });

      if (!res.ok) throw new Error('Failed to update post');
      cancelEdit();
      await fetchPosts();
    } catch (err) {
      setError('Error updating post');
    } finally {
      setUpdating(false);
    }
  };

  useEffect(() => {
    fetchPosts();
  }, []);

  return (
    <div className="min-h-screen bg-gray-50 py-8 px-4 sm:px-6 lg:px-8">
      <div className="max-w-4xl mx-auto">
        <div className="flex justify-between items-center mb-8">
          <h1 className="text-3xl font-bold text-gray-900">Admin Dashboard</h1>
          
        </div>

        {/* Create Post */}
        {/* <div className="bg-white shadow rounded-lg p-6 mb-8">
          <h2 className="text-xl font-semibold mb-4">Create New Post</h2>
          {error && (
            <div className="mb-4 p-3 bg-red-50 text-red-700 rounded-md text-sm">
              {error}
            </div>
          )}
          <div className="space-y-4">
            <input
              id="title"
              type="text"
              value={title}
              onChange={(e) => setTitle(e.target.value)}
              className="w-full px-3 py-2 border border-gray-300 rounded-md"
              placeholder="Post title"
            />
            <textarea
              id="content"
              rows={4}
              value={content}
              onChange={(e) => setContent(e.target.value)}
              className="w-full px-3 py-2 border border-gray-300 rounded-md"
              placeholder="Write your post content here..."
            />
            <div className="flex justify-end">
              <button
                onClick={createPost}
                disabled={isCreating}
                className="px-4 py-2 bg-blue-600 text-white font-medium rounded-md hover:bg-blue-700 disabled:opacity-50"
              >
                {isCreating ? 'Creating...' : 'Create Post'}
              </button>
            </div>
          </div>
        </div> */}

          <div className='flex gap-5'>
            <div className='min-w-40 min-h-40 max-h-40 max-w-40 flex flex-col justify-center items-center shadow-md my-5 bg-white rounded-lg'>
            <h2 className='text-4xl'>{posts.length}</h2>
            <h1 className='text-xl'>Total Posts</h1>
            
          </div>

          <div className='min-w-40 min-h-40 max-h-40 max-w-40 flex flex-col justify-center items-center shadow-md my-5 bg-white rounded-lg'>
            <h2 className='text-4xl'>{posts.length}</h2>
            <h1 className='text-xl'>Total Users</h1>
            
          </div>  
          </div>
          

        {/* Post List */}
        <div className="bg-white shadow rounded-lg overflow-hidden">
          <div className="px-6 py-4 border-b border-gray-200">
            <h2 className="text-xl font-semibold">Your Posts</h2>
          </div>

          {loading ? (
            <div className="p-8 flex justify-center">
              <div className="animate-spin rounded-full h-8 w-8 border-b-2 border-blue-500"></div>
            </div>
          ) : posts.length === 0 ? (
            <div className="p-8 text-center text-gray-500">
              You haven't created any posts yet.
            </div>
          ) : (
            <ul className="divide-y divide-gray-200">
              {posts.map((post) => (
                <li key={post.id} className="p-6 hover:bg-gray-50">
                  <div className="flex justify-between items-start">
                    <div className="flex-1 min-w-0">
                      {editingPostId === post.id ? (
                        <>
                          <input
                            value={editedTitle}
                            onChange={(e) => setEditedTitle(e.target.value)}
                            className="w-full border px-2 py-1 rounded mb-2"
                            placeholder="Edit title"
                          />
                          <textarea
                            value={editedContent}
                            onChange={(e) => setEditedContent(e.target.value)}
                            className="w-full border px-2 py-1 rounded mb-2"
                            rows={3}
                            placeholder="Edit content"
                          />
                          <div className="space-x-2">
                            <button
                              onClick={() => updatePost(post.id)}
                              disabled={updating}
                              className="text-sm text-white bg-green-600 hover:bg-green-700 px-3 py-1 rounded disabled:opacity-50"
                            >
                              {updating ? 'Saving...' : 'Save'}
                            </button>
                            <button
                              onClick={cancelEdit}
                              className="text-sm text-gray-600 hover:text-gray-900 px-3 py-1 rounded"
                            >
                              Cancel
                            </button>
                          </div>
                        </>
                      ) : (
                        <>
                          <h3 className="text-lg font-medium text-gray-900 mb-1">{post.title}</h3>
                          <p className="text-gray-600 mb-2 whitespace-pre-line">{post.content}</p>
                          <div className="text-sm text-gray-500">
                            {post.createdAt && (
                              <span>
                                Created: {new Date(post.createdAt).toLocaleDateString()}
                              </span>
                            )}
                            {post.updatedAt && post.updatedAt !== post.createdAt && (
                              <span className="ml-2">
                                • Updated: {new Date(post.updatedAt).toLocaleDateString()}
                              </span>
                            )}
                          </div>
                        </>
                      )}
                    </div>
                    <div className="ml-4 flex-shrink-0 flex space-x-2">
                      {editingPostId !== post.id && (
                        <>
                          <button
                            onClick={() => handleEdit(post)}
                            className="px-3 py-1 text-sm text-blue-600 hover:text-blue-800"
                          >
                            Edit
                          </button>
                          <button
                            onClick={() => deletePost(post.id)}
                            disabled={deletingId === post.id}
                            className="px-3 py-1 text-sm text-red-600 hover:text-red-800 disabled:opacity-50"
                          >
                            {deletingId === post.id ? 'Deleting...' : 'Delete'}
                          </button>
                        </>
                      )}
                    </div>
                  </div>
                </li>
              ))}
            </ul>
          )}
        </div>
      </div>
    </div>
  );
}
