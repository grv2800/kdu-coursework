import React, { useRef } from 'react';

export function Focus() {
  const firstInputRef = useRef<HTMLInputElement | null>(null);

  const focusFirstInput = (event: React.MouseEvent<HTMLButtonElement, MouseEvent>) => {
    event.preventDefault(); // Prevent the default action
    if (firstInputRef.current) {
      firstInputRef.current.focus();
    }
  };

  return (
    <form>
      <input type="text" ref={firstInputRef} />
      <input type="text" />
      <button onClick={focusFirstInput}>Focus on First Input</button>
    </form>
  );
}

