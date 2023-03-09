import {
    html,
    TemplateResult,
} from "lit";
import {customElement} from "lit/decorators.js";
import {AnyAction, Store} from "@reduxjs/toolkit";
import manager from "@openremote/core";
import {AppStateKeyed, OrApp} from "@openremote/or-app";

import "./custom-header"

@customElement("custom-app")
export class CustomApp<S extends AppStateKeyed> extends OrApp<S> {
    constructor(store: Store<S, AnyAction>) {
        super(store);
    }

    protected render(): TemplateResult | void {
        if (!this._initialised) {
            return html`<or-mwc-dialog id="app-modal"></or-mwc-dialog>`;
        }
        let consoleStyles;
        if (manager.consoleAppConfig) {
            const consoleAppConfig = manager.consoleAppConfig;
            const primary = consoleAppConfig.primaryColor;
            const secondary = consoleAppConfig.secondaryColor;
            consoleStyles = html`<style>:host {--or-console-primary-color:${primary};--or-console-secondary-color:${secondary};}</style>`;
        }
        return html`
            ${this._config.styles ? typeof(this._config.styles) === "string" ? html`<style>${this._config.styles}</style>` : this._config.styles.strings : ``}
            ${consoleStyles}
            ${this._config.header ? html`
                <custom-header .activeMenu="${this._activeMenu}" .store="${this._store}" .realm="${this._realm}" .realms="${this._realms}" .logo="${this._config.logo}" .logoMobile="${this._config.logoMobile}" .config="${this._config.header}"></or-header>
            ` : ``}

            <!-- Main content -->
            <main role="main" class="main-content d-none"></main>

            <slot></slot>
        `;
    }
}