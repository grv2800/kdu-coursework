import  { useRef } from 'react';

function ScrollToTop() {
  const contentRef = useRef<HTMLDivElement>(null);

  const scrollToTop = () => {
    if (contentRef.current) {
      contentRef.current.scrollIntoView({ behavior: 'smooth' });
    }
  };

  return (
    <div>
      
      <div ref={contentRef}>
        {/* Your content here */}
        {Array.from({ length: 50 }).map((_, index) => (
          <p key={index}>Content {index + 1}</p>
        ))}
      </div>
      <button onClick={scrollToTop}>Scroll to Top</button>
    </div>
  );
}

export default ScrollToTop;
