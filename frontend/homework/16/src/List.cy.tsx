import { List } from "./List";
import { Provider } from "react-redux";
import { store } from "./store";

describe("<List />", () => {
  it("renders", () => {
    // see: https://on.cypress.io/mounting-react
    cy.mount(
      <Provider store={store}>
        <List />
      </Provider>
    );
    cy.get('[data-testid="list"]').should("exist");
  });
  it("adds a new item", () => {
    cy.mount(
      <Provider store={store}>
        <List />
      </Provider>
    );
    const newItemText = "New Item";

    cy.get('[data-testid="item-input"]').type(newItemText);
    cy.get('[ data-testid="submit-button"]').click();

    cy.contains(newItemText).should("exist");
  });

  it("deletes an item", () => {
    cy.mount(
      <Provider store={store}>
        <List />
      </Provider>
    );
    cy.get('[data-testid="list"]').as("list");

    cy.get("@list")
      .find("li")
      .its("length")
      .then((initialLength) => {
        if (initialLength > 0) {
          cy.get("@list").find("li").first().find(".listItemImg").click();
          cy.get("@list")
            .find("li")
            .its("length")
            .should("be.lessThan", initialLength);
        }
      });
  });

  it("clears completed items", () => {
    cy.mount(
      <Provider store={store}>
        <List />
      </Provider>
    );
    cy.get('[data-testid="list"]').as("list");
    const newItemText = "New Item";

    cy.get("#itemInput").type(newItemText);
    cy.get('[data-testid="submit-button"]').click();

    cy.get(".listItem").first().find('input[type="checkbox"]').check();

    cy.get(".clearButton").click();

    cy.contains(newItemText).should("not.exist");
  });
});
