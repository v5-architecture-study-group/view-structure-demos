package com.example.demo.viewstructures.iteration6;

import com.example.demo.viewstructures.data.ExampleDTO;
import com.example.demo.viewstructures.data.ExampleEnum;
import com.example.demo.viewstructures.data.ExampleNodeDTO;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.treegrid.TreeGrid;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility;

@Route("iteration6")
public class ExampleRoute extends BaseView {

    private final Grid<ExampleDTO> grid = new Grid<>();
    private final TextField search = new TextField(null, "Search");
    private final Button refresh = new Button(VaadinIcon.REFRESH.create());
    private final Button add = new Button("Create", VaadinIcon.PLUS_CIRCLE.create());
    private final Button edit = new Button(VaadinIcon.EDIT.create());
    private final Button remove = new Button(VaadinIcon.MINUS_CIRCLE.create());
    private final Button alerts = new Button(VaadinIcon.ALARM.create());
    private final Button settings = new Button(VaadinIcon.COGS.create());
    private final Button users = new Button(VaadinIcon.USERS.create());
    private final Button logout = new Button(VaadinIcon.POWER_OFF.create());
    private final Button toggleLeftContent = new Button(VaadinIcon.PADDING_LEFT.create());
    private final Button toggleRightContent = new Button(VaadinIcon.PADDING_RIGHT.create());
    private final Select<ExampleEnum> select = new Select<>();
    private final Span badge = new Span("Some warning");
    private final TreeGrid<ExampleNodeDTO> treeGrid = new TreeGrid<>();

    public ExampleRoute() {
        add.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        select.setItems(ExampleEnum.values());

        badge.getElement().getThemeList().add("badge error");

        grid.addColumn(ExampleDTO::first).setHeader("First");
        grid.addColumn(ExampleDTO::second).setHeader("Second");
        grid.addColumn(ExampleDTO::third).setHeader("Third");
        grid.addColumn(ExampleDTO::fourth).setHeader("Fourth");
        grid.addColumn(ExampleDTO::fifth).setHeader("Fifth");
        grid.addColumn(ExampleDTO::sixth).setHeader("Sixth");
        grid.setSizeFull();
        grid.addThemeVariants(GridVariant.LUMO_COMPACT, GridVariant.LUMO_ROW_STRIPES);
        grid.setItems(ExampleDTO.generate(200));

        treeGrid.addHierarchyColumn(ExampleNodeDTO::first).setHeader("First");
        treeGrid.addColumn(ExampleNodeDTO::second).setHeader("Second");
        treeGrid.setSizeFull();
        treeGrid.addThemeVariants(GridVariant.LUMO_COMPACT, GridVariant.LUMO_ROW_STRIPES, GridVariant.LUMO_NO_BORDER);
        treeGrid.setItems(ExampleNodeDTO.generate(200, 10), ExampleNodeDTO::children);

        var tabs = new Tabs();
        tabs.add(new Tab(VaadinIcon.DESKTOP.create()));
        tabs.add(new Tab(VaadinIcon.CLOCK.create()));
        tabs.add(new Tab(VaadinIcon.COMMENT.create()));
        tabs.add(new Tab(VaadinIcon.PACKAGE.create()));
        tabs.add(new Tab(VaadinIcon.HOSPITAL.create()));
        tabs.setWidthFull();

        var sideBar = new VerticalLayout();
        sideBar.addClassName(LumoUtility.Background.BASE);
        sideBar.setSizeFull();
        sideBar.add(tabs);
        sideBar.setPadding(false);
        sideBar.setSpacing(false);

        withTopLeftComponents(toggleLeftContent, alerts, users)
                .withTopRightComponents(logout, toggleRightContent)
                .withBottomLeftComponents(settings, select)
                .withBottomRightComponents(badge)
                .withMainContent(new ContainerWithToolbars()
                        .withContent(grid)
                        .withTopLeftComponents(add)
                        .withTopRightComponents(search, refresh)
                        .withBottomRightComponents(edit, remove)
                        .withPadding()
                        .withSpacing())
                .withLeftContent(treeGrid)
                .withRightContent(sideBar)
        ;

        toggleLeftContent.addClickListener(event -> toggleLeftContentVisible());
        toggleRightContent.addClickListener(event -> toggleRightContentVisible());
    }
}
