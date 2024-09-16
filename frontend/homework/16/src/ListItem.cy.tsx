import { ListItem } from './ListItem';
import { mount } from 'cypress/react18';


describe('<ListItem />', () => {
  it('renders', () => {
    const text = 'Example item';
    const completed = false;

    mount(
      <ListItem
        text={text}
        completed={completed}
        onDelete={() => {}}
        onToggleCompleted={() => {}}
      />
    );

    cy.contains('label', text).should('exist');
    cy.get('input[type="checkbox"]').should('exist');
    cy.get('img').should('exist');
  });

  it('toggles completed status', () => {
    const text = 'Example item';
    let completed = false;

    mount(
      <ListItem
        text={text}
        completed={completed}
        onDelete={() => {}}
        onToggleCompleted={() => {
          completed = !completed;
        }}
      />
    );
    cy.get('input[type="checkbox"]').check().should('be.checked');
    cy.get('label').should('have.css', 'text-decoration', 'line-through');
  });

  it('deletes item', () => {
    const text = 'Example item';

    // Define a flag to track deletion
    let deleted = true;

    mount(
      <ListItem
        text={text}
        completed={false}
        onDelete={() => {
          deleted = false;
        }}
        onToggleCompleted={() => {}}
      />
    );
    cy.get('[data-testid="delete-button"]').click();

    cy.wrap(deleted).should('be.false');
  });

});
