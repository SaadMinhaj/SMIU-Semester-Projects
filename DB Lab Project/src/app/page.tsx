
export default function Home() {
  return (
    
      
      
      <main className="flex-grow">
        {/* Hero Section */}
        <section className="bg-gradient-to-r from-blue-50 to-indigo-50 py-20">
          <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
            <div className="text-center">
              <h1 className="text-4xl md:text-6xl font-bold text-gray-900 mb-6">
                Write, Publish, and <span className="text-blue-600">Connect</span>
              </h1>
              <p className="text-xl text-gray-600 max-w-3xl mx-auto mb-10">
                A modern blogging platform that helps you share your ideas with the world.
              </p>
              <div className="flex flex-col sm:flex-row justify-center gap-4">
                <a
                  href="/signup"
                  className="px-8 py-4 bg-blue-600 hover:bg-blue-700 text-white font-medium rounded-lg shadow-lg hover:shadow-xl transition-all duration-200"
                >
                  Start Your Blog - It's Free
                </a>
                <a
                  href="/features"
                  className="px-8 py-4 border border-gray-300 hover:border-gray-400 text-gray-700 font-medium rounded-lg bg-white hover:bg-gray-50 transition-all duration-200"
                >
                  Learn More
                </a>
              </div>
            </div>
          </div>
        </section>

        {/* Features Section */}
        <section className="py-20">
          <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
            <div className="text-center mb-16">
              <h2 className="text-3xl font-bold text-gray-900 mb-4">Everything you need to blog</h2>
              <p className="text-xl text-gray-600 max-w-3xl mx-auto">
                Powerful features to help you create and grow your audience
              </p>
            </div>
            
            <div className="grid md:grid-cols-3 gap-10">
              {[
                {
                  icon: '✍️',
                  title: 'Beautiful Editor',
                  description: 'Write with our distraction-free editor that supports markdown and rich text.'
                },
                {
                  icon: '🎨',
                  title: 'Custom Themes',
                  description: 'Choose from beautiful templates or create your own unique design.'
                },
                {
                  icon: '📈',
                  title: 'Powerful Analytics',
                  description: 'Understand your audience with detailed traffic and engagement metrics.'
                },
                {
                  icon: '🔒',
                  title: 'Built-in SEO',
                  description: 'Optimize your content for search engines without any technical knowledge.'
                },
                {
                  icon: '🤝',
                  title: 'Community',
                  description: 'Connect with other writers and grow your audience together.'
                },
                {
                  icon: '💰',
                  title: 'Monetization',
                  description: 'Earn money from your content through subscriptions and sponsorships.'
                }
              ].map((feature, index) => (
                <div key={index} className="bg-gray-50 p-8 rounded-xl hover:shadow-md transition-shadow duration-200">
                  <div className="text-4xl mb-4">{feature.icon}</div>
                  <h3 className="text-xl font-semibold mb-3">{feature.title}</h3>
                  <p className="text-gray-600">{feature.description}</p>
                </div>
              ))}
            </div>
          </div>
        </section>

        {/* CTA Section */}
        <section className="bg-blue-600 text-white py-20">
          <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 text-center">
            <h2 className="text-3xl font-bold mb-6">Ready to start your blogging journey?</h2>
            <p className="text-xl mb-10 max-w-3xl mx-auto">
              Join thousands of writers who are already sharing their stories with the world.
            </p>
            <a
              href="/signup"
              className="px-8 py-4 bg-white text-blue-600 font-bold rounded-lg shadow-lg hover:bg-gray-100 transition-all duration-200 inline-block"
            >
              Create Your Free Account
            </a>
          </div>
        </section>
      </main>
      
      
    
  );
}