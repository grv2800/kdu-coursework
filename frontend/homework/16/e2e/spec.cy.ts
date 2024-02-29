describe('template spec', () => {
  it('passes', () => {
    cy.visit('http://localhost:5173')
  })
  it("check add item", () => {
    cy.visit("http://localhost:5173");
    cy.get('[data-testid="item-input"]').should("exist");
    cy.get('[data-testid="item-input"]').type("Test");
    cy.get('[data-testid="submit-button"]').click();
    cy.contains("Test").should("exist");

    cy.get('.listItem-0-2-15 > div > input').first().check();
    const compleletedItem = cy.get('.listItem-0-2-15');
    compleletedItem.should(
      "have.css",
      "text-decoration",
      "line-through solid rgb(0, 0, 0)"
    );
  });
  it("Check Search functionality", () => {
    cy.visit("http://localhost:5173");
    cy.get('[data-testid="item-input"]').should("exist");
    cy.get('[data-testid="item-input"]').type("Test");
    cy.get('[data-testid="submit-button"]').click();
    cy.contains("Test").should("exist");

    const searchInput = cy.get('.input-0-2-13');
    const searchTerm = "Test"; 
    searchInput.type(searchTerm);
    cy.get('.listItem-0-2-15').contains("Test");
  });
  it("check redux persist ", () => {
    cy.visit("http://localhost:5173");
    cy.get('[data-testid="item-input"]').should("exist");
    cy.get('[data-testid="item-input"]').type("Test");
    cy.get('[data-testid="submit-button"]').click();
    cy.reload();
    cy.contains("Test").should("exist");
  });
  it("check delete item ", () => {
    cy.visit("http://localhost:5173");
    cy.get('[data-testid="item-input"]').should("exist");
    cy.get('[data-testid="item-input"]').type("Test");
    cy.get('[data-testid="submit-button"]').click();

    cy.contains("Test").should("exist");
    cy.get('[data-testid="delete-button"]').click();
    cy.contains("Test").should("not.exist");
  });
  it("check clear item ", () => {
    cy.visit("http://localhost:5173");
    cy.get('[data-testid="item-input"]').should("exist");
    cy.get('[data-testid="item-input"]').type("Test 1");
    cy.get('[data-testid="submit-button"]').click();
    cy.get('[data-testid="item-input"]').type("Test 2");
    cy.get('[data-testid="submit-button"]').click();
    
    cy.get('ul > :nth-child(1) > div > input').click();
    cy.get('[data-testid="clear-button"]').click();
    cy.contains("Test 1").should("not.exist");
  });
})