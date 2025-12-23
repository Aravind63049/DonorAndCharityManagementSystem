// === CONFIG ===
const API_BASE = (() => {
	const fallback = 'http://localhost:8080';
	try {
		const override = window.__API_BASE__
			|| document.querySelector('meta[name="api-base"]')?.content
			|| document.body?.dataset?.apiBase
			|| document.documentElement?.dataset?.apiBase;
		if (override && typeof override === 'string') {
			return override.replace(/\/$/, '');
		}
		const { origin, hostname, port, protocol } = window.location;
		if (origin && origin.startsWith('http')) {
			if (!port || port === '8080') {
				return origin.replace(/\/$/, '');
			}
			const localHosts = new Set(['localhost', '127.0.0.1']);
			if (!localHosts.has(hostname)) {
				return origin.replace(/\/$/, '');
			}
			return `${protocol}//${hostname}:8080`;
		}
	} catch (err) {
		// fall back to default base when window is unavailable (e.g. tests)
	}
	return fallback;
})();
const makeEndpoint = (path) => `${API_BASE}${path}`;

const ENDPOINTS = {
	organizations: makeEndpoint('/organizations'),
	campaigns: makeEndpoint('/campaigns'),
	donors: makeEndpoint('/donors'),
	donations: makeEndpoint('/donations'),
	fundAllocations: makeEndpoint('/fund-allocations'),
	users: makeEndpoint('/users')
};

const ACTION_LABELS = {
	create: 'Add New',
	find: 'Find by ID',
	refresh: 'Reload'
};

const ACTION_VARIANTS = {
	create: 'primary',
	find: 'secondary',
	refresh: 'ghost'
};

const ROW_DEFAULT_VARIANTS = {
	edit: 'primary',
	delete: 'danger',
	allocate: 'info'
};

const SCHEMAS = {
	organizations: {
		title: 'Organizations',
		singular: 'Organization',
		endpoint: ENDPOINTS.organizations,
		columns: [
			{ key: 'id', label: 'ID' },
			{ key: 'orgName', label: 'Name' },
			{ key: 'orgAddress', label: 'Address' },
			{ key: 'orgEmail', label: 'Email' }
		],
		form: [
			{ field: 'orgName', label: 'Organization Name', type: 'text', required: true },
			{ field: 'orgAddress', label: 'Organization Address', type: 'text', required: true },
			{ field: 'orgEmail', label: 'Organization Email', type: 'email', required: true }
		],
		actions: ['create', 'find', 'refresh'],
		rowActions: [
			{ action: 'edit', label: 'Edit', variant: 'primary' },
			{ action: 'delete', label: 'Delete', variant: 'danger' }
		]
	},
	campaigns: {
		title: 'Campaigns',
		singular: 'Campaign',
		endpoint: ENDPOINTS.campaigns,
		columns: [
			{ key: 'id', label: 'ID' },
			{ key: 'title', label: 'Title' },
			{ key: 'description', label: 'Description' },
			{ key: 'targetAmount', label: 'Target Amount' },
			{ key: 'collectionAmount', label: 'Collected' },
			{ key: 'startDate', label: 'Start', type: 'datetime' },
			{ key: 'endDateTime', label: 'End', type: 'datetime' },
			{ key: 'organizationId', label: 'Organization ID' }
		],
		form: [
			{ field: 'title', label: 'Title', type: 'text', required: true },
			{ field: 'description', label: 'Description', type: 'textarea', required: true, rows: 3 },
			{ field: 'targetAmount', label: 'Target Amount', type: 'number', required: true },
			{ field: 'collectionAmount', label: 'Collection Amount', type: 'number', optionalOnEdit: true, omitIfEmpty: true },
			{ field: 'startDate', label: 'Start Date', type: 'datetime-local', required: true },
			{ field: 'endDateTime', label: 'End Date', type: 'datetime-local', required: true },
			{ field: 'organizationId', label: 'Organization ID', type: 'number', required: true }
		],
		actions: ['create', 'find', 'refresh'],
		rowActions: [
			{ action: 'edit', label: 'Edit', variant: 'primary' },
			{ action: 'allocate', label: 'Allocate Funds', variant: 'info' },
			{ action: 'delete', label: 'Delete', variant: 'danger' }
		]
	},
	donors: {
		title: 'Donors',
		singular: 'Donor',
		endpoint: ENDPOINTS.donors,
		columns: [
			{ key: 'id', label: 'ID' },
			{ key: 'name', label: 'Name' },
			{ key: 'email', label: 'Email' },
			{ key: 'phoneNumber', label: 'Phone' },
			{ key: 'address', label: 'Address' },
			{ key: 'userId', label: 'User ID' }
		],
		form: [
			{ field: 'name', label: 'Name', type: 'text', required: true },
			{ field: 'email', label: 'Email', type: 'email', required: true },
			{ field: 'phoneNumber', label: 'Phone Number', type: 'text', required: true },
			{ field: 'address', label: 'Address', type: 'textarea', rows: 3, required: true },
			{ field: 'userId', label: 'User ID', type: 'number', required: true }
		],
		actions: ['create', 'find', 'refresh'],
		rowActions: [
			{ action: 'edit', label: 'Edit', variant: 'primary' },
			{ action: 'delete', label: 'Delete', variant: 'danger' }
		]
	},
	donations: {
		title: 'Donations',
		singular: 'Donation',
		endpoint: ENDPOINTS.donations,
		columns: [
			{ key: 'id', label: 'ID' },
			{ key: 'amount', label: 'Amount' },
			{ key: 'donationType', label: 'Type' },
			{ key: 'description', label: 'Description' },
			{ key: 'donationDate', label: 'Donation Date', type: 'datetime' },
			{ key: 'createdAt', label: 'Created At', type: 'datetime' },
			{ key: 'donorId', label: 'Donor ID' },
			{ key: 'campaignId', label: 'Campaign ID' }
		],
		form: [
			{ field: 'amount', label: 'Amount', type: 'number', required: true },
			{ field: 'donationType', label: 'Donation Type', type: 'text', required: true },
			{ field: 'description', label: 'Description', type: 'textarea', rows: 3 },
			{ field: 'donationDate', label: 'Donation Date', type: 'datetime-local' },
			{ field: 'donorId', label: 'Donor ID', type: 'number', required: true },
			{ field: 'campaignId', label: 'Campaign ID', type: 'number', required: true }
		],
		actions: ['create', 'find', 'refresh'],
		rowActions: [
			{ action: 'edit', label: 'Edit', variant: 'primary' },
			{ action: 'delete', label: 'Delete', variant: 'danger' }
		]
	},
	'fund-allocations': {
		title: 'Fund Allocations',
		singular: 'Fund Allocation',
		endpoint: ENDPOINTS.fundAllocations,
		columns: [
			{ key: 'id', label: 'ID' },
			{ key: 'amountUsed', label: 'Amount Used' },
			{ key: 'usageDescription', label: 'Usage Description' },
			{ key: 'usedDate', label: 'Used Date', type: 'datetime' },
			{ key: 'organizationId', label: 'Organization ID' },
			{ key: 'campaignId', label: 'Campaign ID' }
		],
		form: [
			{ field: 'amountUsed', label: 'Amount Used', type: 'number', required: true },
			{ field: 'usageDescription', label: 'Usage Description', type: 'textarea', rows: 3, required: true },
			{ field: 'usedDate', label: 'Used Date', type: 'datetime-local' },
			{ field: 'organizationId', label: 'Organization ID', type: 'number', required: true },
			{ field: 'campaignId', label: 'Campaign ID', type: 'number', required: true }
		],
		actions: ['create', 'find', 'refresh'],
		rowActions: [
			{ action: 'edit', label: 'Edit', variant: 'primary' },
			{ action: 'delete', label: 'Delete', variant: 'danger' }
		]
	},
	users: {
		title: 'Users',
		singular: 'User',
		endpoint: ENDPOINTS.users,
		columns: [
			{ key: 'id', label: 'ID' },
			{ key: 'userName', label: 'User Name' },
			{ key: 'email', label: 'Email' },
			{ key: 'phoneNumber', label: 'Phone Number' },
			{ key: 'role', label: 'Role' }
		],
		form: [
			{ field: 'userName', label: 'User Name', type: 'text', required: true },
			{ field: 'email', label: 'Email', type: 'email', required: true },
			{ field: 'password', label: 'Password', type: 'password', required: true, optionalOnEdit: true, omitIfEmpty: true },
			{ field: 'phoneNumber', label: 'Phone Number', type: 'text' },
			{ field: 'role', label: 'Role', type: 'text', required: true, defaultValue: 'USER' }
		],
		actions: ['create', 'find', 'refresh'],
		rowActions: [
			{ action: 'edit', label: 'Edit', variant: 'primary' },
			{ action: 'delete', label: 'Delete', variant: 'danger' }
		]
	}
};

// === STATE ===
const state = {
	currentPage: 'dashboard',
	currentSchema: null,
	currentListContainer: null,
	editingItem: null,
	dataMap: {}
};

// === DOM REFERENCES ===
const pages = {
	dashboard: document.getElementById('dashboard-page'),
	list: document.getElementById('list-page'),
	organizations: document.getElementById('organizations-page'),
	campaigns: document.getElementById('campaigns-page'),
	donors: document.getElementById('donors-page'),
	donations: document.getElementById('donations-page'),
	'fund-allocations': document.getElementById('fund-allocations-page'),
	users: document.getElementById('users-page')
};
const formArea = document.getElementById('form-area');
const modalEl = document.getElementById('modal');

// === AUTH HELPERS ===
function getStoredAuth() {
	return localStorage.getItem('auth_credentials');
}
function setStoredAuth(token) {
	if (token) {
		localStorage.setItem('auth_credentials', token);
	} else {
		localStorage.removeItem('auth_credentials');
	}
}
function authHeader() {
	const token = getStoredAuth();
	return token ? { Authorization: 'Basic ' + token } : {};
}
function isAuthenticated() {
	return !!getStoredAuth();
}
function setActiveUserDisplay(label) {
	const signedIn = isAuthenticated();
	document.getElementById('current-user').textContent = label || (signedIn ? 'Signed in' : 'Not signed in');
	document.getElementById('btn-login').style.display = signedIn ? 'none' : '';
	document.getElementById('btn-logout').style.display = signedIn ? 'inline-block' : 'none';
}

// === NETWORK HELPER ===
async function safeFetch(url, opts = {}) {
	const options = { ...opts };
	options.headers = { ...(opts.headers || {}), ...authHeader() };
	if (!options.headers.Accept) {
		options.headers.Accept = 'application/json, text/plain;q=0.9';
	}
	if (options.body && !(options.body instanceof FormData) && !options.headers['Content-Type']) {
		options.headers['Content-Type'] = 'application/json';
	}
	options.credentials = 'include';
	let res;
	try {
		res = await fetch(url, options);
	} catch (err) {
		const networkMessage = err && err.message ? err.message : 'Network request failed';
		throw new Error(stripHtml(networkMessage));
	}
	if (res.status === 401) {
		throw new Error('Unauthorized');
	}
	const contentType = res.headers.get('content-type') || '';
	if (!res.ok) {
		if (contentType.includes('application/json')) {
			const data = await res.json().catch(() => null);
			const message = data && (data.message || data.error || JSON.stringify(data));
			throw new Error(message || res.statusText || `Request failed with status ${res.status}`);
		}
		const text = await res.text().catch(() => '');
		const friendly = stripHtml(text).slice(0, 300);
		throw new Error(friendly || res.statusText || `Request failed with status ${res.status}`);
	}
	if (res.status === 204) {
		return null;
	}
	if (contentType.includes('application/json')) {
		return res.json();
	}
	return res.text();
}

// === UI HELPERS ===
function getActivePageKey() {
	const activeBtn = document.querySelector('#menu button.active');
	return activeBtn ? activeBtn.dataset.page : 'dashboard';
}
function resolveListElements(pageKey) {
	const pageEl = pages[pageKey] || null;
	if (!pageEl) {
		return { pageEl: null, infoEl: null, container: null };
	}
	return {
		pageEl,
		infoEl: pageEl.querySelector('.list-info'),
		container: pageEl.querySelector('.list-container')
	};
}
function setCurrentSchema(pageKey) {
	const schema = SCHEMAS[pageKey];
	if (!schema) {
		return null;
	}
	state.currentPage = pageKey;
	state.currentSchema = schema;
	const { container } = resolveListElements(pageKey);
	state.currentListContainer = container || null;
	return schema;
}
function escapeHtml(value) {
	return String(value)
		.replace(/&/g, '&amp;')
		.replace(/</g, '&lt;')
		.replace(/>/g, '&gt;')
		.replace(/"/g, '&quot;')
		.replace(/'/g, '&#39;');
}
function formatDateTime(value) {
	if (!value) {
		return '';
	}
	const asString = typeof value === 'string' ? value : String(value);
	const parsed = new Date(asString);
	if (!Number.isNaN(parsed.getTime())) {
		return parsed.toLocaleString();
	}
	return asString.replace('T', ' ');
}
function formatCellValue(column, rawValue) {
	if (rawValue === null || rawValue === undefined) {
		return '';
	}
	if (column.type === 'datetime' || /date|time/i.test(column.key)) {
		return formatDateTime(rawValue);
	}
	return rawValue;
}
function stripHtml(input = '') {
	return input
		.replace(/<style[\s\S]*?>[\s\S]*?<\/style>/gi, ' ')
		.replace(/<script[\s\S]*?>[\s\S]*?<\/script>/gi, ' ')
		.replace(/<[^>]*>/g, ' ')
		.replace(/&nbsp;/gi, ' ')
		.replace(/\s+/g, ' ')
		.trim();
}
function buildRowButtonHtml(cfg, item, entityKey) {
	const recordId = item.id ?? item[cfg.idField || 'id'];
	if (recordId === undefined || recordId === null) {
		return '';
	}
	const variant = cfg.variant || ROW_DEFAULT_VARIANTS[cfg.action] || 'secondary';
	const label = cfg.label || cfg.action.charAt(0).toUpperCase() + cfg.action.slice(1);
	return `<button class="btn ${variant}" data-row-action="${cfg.action}" data-entity="${entityKey}" data-id="${recordId}">${escapeHtml(label)}</button>`;
}

// === DATA BINDING ===
function renderTable(schema, items, container, pageKey = state.currentPage) {
	if (!container || !schema) {
		return;
	}
	const data = Array.isArray(items) ? items : (items ? [items] : []);
	state.dataMap[pageKey] = data;
	if (!data.length) {
		container.innerHTML = '<div class="panel small">No records found.</div>';
		return;
	}
	const includeActions = !!(schema.rowActions && schema.rowActions.length);
	let html = '<table><thead><tr>';
	html += schema.columns.map((col) => `<th>${escapeHtml(col.label || col.key)}</th>`).join('');
	if (includeActions) {
		html += '<th>Actions</th>';
	}
	html += '</tr></thead><tbody>';
	data.forEach((item) => {
		html += '<tr>';
		schema.columns.forEach((col) => {
			const formatted = formatCellValue(col, item[col.key]);
			html += `<td>${escapeHtml(formatted)}</td>`;
		});
		if (includeActions) {
			const buttons = schema.rowActions.map((cfg) => buildRowButtonHtml(cfg, item, pageKey)).join('');
			html += `<td class="table-actions">${buttons}</td>`;
		}
		html += '</tr>';
	});
	html += '</tbody></table>';
	container.innerHTML = html;
}

async function loadList(pageKey) {
  const schema = setCurrentSchema(pageKey);
  const { infoEl, container } = resolveListElements(pageKey);
  if (!schema || !container) return;

  if (infoEl) {
    infoEl.textContent = schema.title;
  }

  // 🔴 hide data when signed out for everything EXCEPT "users"
  if (!isAuthenticated() && pageKey !== 'users') {
    state.dataMap[pageKey] = [];
    container.innerHTML = '<div class="panel small muted">Data hidden while signed out.</div>';
    return;
  }

  container.innerHTML = '<div class="panel small">Loading...</div>';
  try {
    const payload = await safeFetch(schema.endpoint);
    renderTable(schema, payload || [], container, pageKey);
  } catch (err) {
    container.innerHTML = `<div class="panel warning">${escapeHtml(err.message)}</div>`;
  }
}


async function loadCounts() {
	const counts = {
		org: document.getElementById('count-org'),
		campaign: document.getElementById('count-campaign'),
		donor: document.getElementById('count-donor'),
		donation: document.getElementById('count-donation')
	};
	if (!isAuthenticated()) {
		counts.org.textContent = '—';
		counts.campaign.textContent = '—';
		counts.donor.textContent = '—';
		counts.donation.textContent = '—';
		return;
	}
	try {
		const [orgs, camps, donors, donations] = await Promise.all([
			safeFetch(ENDPOINTS.organizations),
			safeFetch(ENDPOINTS.campaigns),
			safeFetch(ENDPOINTS.donors),
			safeFetch(ENDPOINTS.donations)
		]);
		counts.org.textContent = (orgs || []).length;
		counts.campaign.textContent = (camps || []).length;
		counts.donor.textContent = (donors || []).length;
		const total = (donations || []).reduce((sum, donation) => {
			const value = Number(donation.amount || 0);
			return Number.isFinite(value) ? sum + value : sum;
		}, 0);
		counts.donation.textContent = total;
	} catch (err) {
		console.warn('loadCounts failed', err);
		counts.org.textContent = '—';
		counts.campaign.textContent = '—';
		counts.donor.textContent = '—';
		counts.donation.textContent = '—';
	}
}

// === MODAL HELPERS ===
function modalSetHidden(hidden) {
	modalEl.setAttribute('aria-hidden', hidden ? 'true' : 'false');
	if (hidden) {
		formArea.innerHTML = '';
		state.editingItem = null;
	}
}
function valueForInput(fieldCfg, raw) {
	if (raw === null || raw === undefined) {
		return '';
	}
	if (fieldCfg.type === 'number') {
		return raw;
	}
	if (fieldCfg.type === 'datetime-local') {
		const str = typeof raw === 'string' ? raw : String(raw);
		return str.length >= 16 ? str.slice(0, 16) : str;
	}
	return raw;
}
function openModal(mode, schema, item = null) {
	if (!schema) {
		return;
	}
	state.currentSchema = schema;
	state.editingItem = mode === 'edit' ? item : null;
	const modalTitle = document.getElementById('modal-title');
	const modalSubtitle = document.getElementById('modal-subtitle');
	const singular = schema.singular || schema.title.replace(/s$/i, '');
	modalTitle.textContent = `${mode === 'edit' ? 'Edit' : 'Create'} ${singular}`;
	if (modalSubtitle) {
		const actionText = mode === 'edit' ? 'Update the details below.' : 'Complete the form to add a new record.';
		modalSubtitle.textContent = actionText;
	}
	formArea.innerHTML = '';
	schema.form.forEach((fieldCfg) => {
		const wrapper = document.createElement('div');
		wrapper.className = 'form-field';
		const label = document.createElement('label');
		label.textContent = fieldCfg.label || fieldCfg.field;
		const isEdit = mode === 'edit';
		const elementType = fieldCfg.type === 'textarea' ? 'textarea' : 'input';
		const control = document.createElement(elementType);
		if (elementType === 'input') {
			control.type = fieldCfg.type || 'text';
		} else if (fieldCfg.rows) {
			control.rows = fieldCfg.rows;
		} else {
			control.rows = 3;
		}
		control.setAttribute('data-field', fieldCfg.field);
		const required = fieldCfg.required && !(isEdit && fieldCfg.optionalOnEdit);
		if (required) {
			control.required = true;
		}
		if (fieldCfg.placeholder) {
			control.placeholder = fieldCfg.placeholder;
		}
		const initialValue = item ? valueForInput(fieldCfg, item[fieldCfg.field]) : (fieldCfg.defaultValue || '');
		control.value = initialValue || '';
		wrapper.appendChild(label);
		wrapper.appendChild(control);
		formArea.appendChild(wrapper);
	});
	modalSetHidden(false);
}
function collectFormPayload(schema) {
	const payload = {};
	const errors = [];
	const isEdit = !!state.editingItem;
	schema.form.forEach((fieldCfg) => {
		const control = formArea.querySelector(`[data-field="${fieldCfg.field}"]`);
		if (!control) {
			return;
		}
		const rawValue = control.value.trim();
		const required = fieldCfg.required && !(isEdit && fieldCfg.optionalOnEdit);
		if (required && !rawValue) {
			errors.push(`${fieldCfg.label || fieldCfg.field} is required`);
			return;
		}
		if (!rawValue) {
			if (fieldCfg.omitIfEmpty) {
				return;
			}
			payload[fieldCfg.field] = null;
			return;
		}
		if (fieldCfg.type === 'number') {
			const numeric = Number(rawValue);
			if (!Number.isFinite(numeric)) {
				errors.push(`${fieldCfg.label || fieldCfg.field} must be a numeric value`);
				return;
			}
			payload[fieldCfg.field] = numeric;
			return;
		}
		if (fieldCfg.type === 'datetime-local') {
			const normalized = rawValue.length === 16 ? `${rawValue}:00` : rawValue;
			payload[fieldCfg.field] = normalized;
			return;
		}
		payload[fieldCfg.field] = rawValue;
	});
	return { payload, errors };
}
async function onSaveModal() {
	const schema = state.currentSchema;
	if (!schema) {
		return;
	}
	const { payload, errors } = collectFormPayload(schema);
	if (errors.length) {
		alert(`Please resolve the following issues:\n- ${errors.join('\n- ')}`);
		return;
	}
	const isEdit = !!state.editingItem;
	const targetUrl = isEdit ? `${schema.endpoint}/${state.editingItem.id}` : schema.endpoint;
	const method = isEdit ? 'PUT' : 'POST';
	const isUserEntity = state.currentPage === 'users';
	const creatingUserWithoutAuth = isUserEntity && !isEdit && !isAuthenticated();
	try {
		if (creatingUserWithoutAuth) {
			const res = await fetch(targetUrl, {
				method,
				headers: { 'Content-Type': 'application/json' },
				body: JSON.stringify(payload)
			});
			if (!res.ok) {
				const text = await res.text().catch(() => res.statusText);
				throw new Error(text || res.statusText);
			}
		} else {
			await safeFetch(targetUrl, {
				method,
				body: JSON.stringify(payload)
			});
		}
		modalSetHidden(true);
		if (creatingUserWithoutAuth) {
			alert('User created. Please sign in with the new credentials.');
		} else {
			loadList(state.currentPage);
		}
	} catch (err) {
		alert(`Save failed: ${err.message}`);
	}
}

// === ACTION HANDLERS ===
async function handleFindById(schema, entityKey) {
	const id = prompt(`Enter ${schema.singular || schema.title} ID`);
	if (!id) {
		return;
	}
	const { container, infoEl } = resolveListElements(entityKey);
	if (!container) {
		return;
	}
	try {
		const record = await safeFetch(`${schema.endpoint}/${id}`);
		if (infoEl) {
			infoEl.textContent = `${schema.title} (ID ${id})`;
		}
		renderTable(schema, record ? [record] : [], container, entityKey);
	} catch (err) {
		alert(`Lookup failed: ${err.message}`);
	}
}
function handleToolbarAction(button) {
	const action = button.dataset.action;
	const entityKey = button.dataset.entity;
	const schema = setCurrentSchema(entityKey);
	if (!schema) {
		return;
	}
	const isCreate = action === 'create';
	const isUserCreate = isCreate && entityKey === 'users';
	if (!isAuthenticated()) {
		if (isCreate && !isUserCreate) {
			alert('Please sign in to create records.');
			return;
		}
		if (action === 'find' || action === 'refresh') {
			return;
		}
	}
	switch (action) {
		case 'create':
			openModal('create', schema);
			break;
		case 'find':
			handleFindById(schema, entityKey);
			break;
		case 'refresh':
			loadList(entityKey);
			break;
		default:
			break;
	}
}
function handleEdit(entityKey, id) {
	const schema = setCurrentSchema(entityKey);
	if (!schema) {
		return;
	}
	const data = state.dataMap[entityKey] || [];
	const record = data.find((item) => String(item.id) === String(id));
	if (record) {
		openModal('edit', schema, record);
		return;
	}
	safeFetch(`${schema.endpoint}/${id}`)
		.then((result) => openModal('edit', schema, result))
		.catch((err) => alert(`Unable to load record: ${err.message}`));
}
async function handleDelete(entityKey, id) {
	const schema = setCurrentSchema(entityKey);
	if (!schema) {
		return;
	}
	const singular = schema.singular || schema.title.replace(/s$/i, '');
	if (!confirm(`Delete ${singular} #${id}?`)) {
		return;
	}
	try {
		await safeFetch(`${schema.endpoint}/${id}`, { method: 'DELETE' });
		loadList(entityKey);
	} catch (err) {
		alert(`Delete failed: ${err.message}`);
	}
}
async function handleAllocate(entityKey, id) {
	const organizationIdInput = prompt('Organization ID to allocate funds to:');
	if (!organizationIdInput) {
		return;
	}
	const amountInput = prompt('Amount to allocate:');
	if (!amountInput) {
		return;
	}
	const organizationId = Number(organizationIdInput);
	const amount = Number(amountInput);
	if (!Number.isFinite(organizationId) || !Number.isFinite(amount) || amount <= 0) {
		alert('Please provide valid numeric values for organization ID and amount.');
		return;
	}
	const note = prompt('Usage note (optional):') || null;
	try {
		await safeFetch(`${SCHEMAS[entityKey].endpoint}/${id}/allocate`, {
			method: 'POST',
			body: JSON.stringify({ organizationId, amount, note })
		});
		alert('Funds allocated successfully.');
		loadList(entityKey);
	} catch (err) {
		alert(`Allocation failed: ${err.message}`);
	}
}
function handleRowAction(button) {
	const action = button.dataset.rowAction;
	const entityKey = button.dataset.entity;
	const id = button.dataset.id;
	if (!action || !entityKey || !id) {
		return;
	}
	if (!isAuthenticated()) {
		alert('Please sign in to manage records.');
		return;
	}
	switch (action) {
		case 'edit':
			handleEdit(entityKey, id);
			break;
		case 'delete':
			handleDelete(entityKey, id);
			break;
		case 'allocate':
			handleAllocate(entityKey, id);
			break;
		default:
			break;
	}
}
function globalClickHandler(event) {
	const rowBtn = event.target.closest('button[data-row-action]');
	if (rowBtn) {
		handleRowAction(rowBtn);
		return;
	}
	const toolbarBtn = event.target.closest('button[data-action]');
	if (toolbarBtn) {
		handleToolbarAction(toolbarBtn);
	}
}

// === UI INITIALIZATION ===
function renderActionToolbars() {
	Object.entries(SCHEMAS).forEach(([key, schema]) => {
		const { pageEl } = resolveListElements(key);
		if (!pageEl) {
			return;
		}
		const toolbar = pageEl.querySelector('.list-actions');
		if (!toolbar) {
			return;
		}
		toolbar.innerHTML = '';
		const actions = schema.actions || ['create', 'find', 'refresh'];
		actions.forEach((action) => {
			const btn = document.createElement('button');
			btn.className = `btn ${ACTION_VARIANTS[action] || 'secondary'}`;
			btn.textContent = ACTION_LABELS[action] || action;
			btn.dataset.action = action;
			btn.dataset.entity = key;
			toolbar.appendChild(btn);
		});
	});
}
function showPage(pageKey, buttonLabel) {
	Object.entries(pages).forEach(([key, el]) => {
		if (!el) {
			return;
		}
		el.style.display = key === pageKey ? '' : 'none';
	});
	document.getElementById('page-title').textContent = buttonLabel || '';
	if (pageKey === 'dashboard') {
		state.currentPage = 'dashboard';
		loadCounts();
		return;
	}
	loadList(pageKey);
}
function initializeMenu() {
	document.querySelectorAll('#menu button').forEach((btn) => {
		btn.addEventListener('click', () => {
			document.querySelectorAll('#menu button').forEach((other) => other.classList.remove('active'));
			btn.classList.add('active');
			showPage(btn.dataset.page, btn.textContent);
		});
	});
}
function initializeAuthControls() {
	document.getElementById('btn-login').addEventListener('click', () => {
		const username = prompt('Username (userName or email):');
		if (!username) {
			return;
		}
		const password = prompt('Password:');
		if (password === null) {
			return;
		}
		setStoredAuth(btoa(`${username}:${password}`));
		setActiveUserDisplay(username);
		loadCounts();
		const active = getActivePageKey();
		if (active && active !== 'dashboard') {
			loadList(active);
		}
	});
	document.getElementById('btn-logout').addEventListener('click', () => {
		setStoredAuth(null);
		setActiveUserDisplay('Not signed in');
		state.dataMap = {};
		loadCounts();
		const active = getActivePageKey();
		if (active && active !== 'dashboard') {
			loadList(active);
		}
	});
}
function initializeUserModal() {
	const userModal = document.getElementById('user-modal');
	if (!userModal) {
		return;
	}
	const openBtn = document.getElementById('btn-user-create');
	const cancelBtn = document.getElementById('u_cancel');
	const saveBtn = document.getElementById('u_save');
	const errorEl = document.getElementById('u_error');
	const inputs = {
		userName: document.getElementById('u_username'),
		email: document.getElementById('u_email'),
		password: document.getElementById('u_password'),
		phone: document.getElementById('u_phone'),
		role: document.getElementById('u_role')
	};
	const showError = (message) => {
		if (!errorEl) {
			return;
		}
		errorEl.textContent = message || '';
		errorEl.style.display = message ? 'block' : 'none';
	};
	const resetForm = () => {
		Object.entries(inputs).forEach(([key, input]) => {
			if (!input) {
				return;
			}
			if (key === 'role') {
				input.value = 'USER';
				return;
			}
			input.value = '';
		});
		showError('');
	};
	const setHidden = (hidden) => {
		userModal.setAttribute('aria-hidden', hidden ? 'true' : 'false');
		if (!hidden && inputs.userName) {
			setTimeout(() => inputs.userName.focus(), 120);
		}
	};
	openBtn?.addEventListener('click', () => {
		resetForm();
		setHidden(false);
	});
	cancelBtn?.addEventListener('click', () => {
		setHidden(true);
	});
	userModal.addEventListener('click', (event) => {
		if (event.target === userModal) {
			setHidden(true);
		}
	});
	document.addEventListener('keydown', (event) => {
		if (event.key === 'Escape' && userModal.getAttribute('aria-hidden') === 'false') {
			setHidden(true);
		}
	});
	saveBtn?.addEventListener('click', async () => {
		const payload = {
			userName: inputs.userName?.value?.trim() || null,
			email: inputs.email?.value?.trim() || null,
			password: inputs.password?.value || null,
			phoneNumber: inputs.phone?.value?.trim() || null,
			role: inputs.role?.value?.trim() || 'USER'
		};
		if (!payload.userName || !payload.email || !payload.password) {
			alert('Username, email, and password are required.');
			if (!payload.userName && inputs.userName) {
				inputs.userName.focus();
			}
			return;
		}
		saveBtn.disabled = true;
		saveBtn.textContent = 'Creating…';
		showError('');
		try {
			const res = await fetch(ENDPOINTS.users, {
				method: 'POST',
				headers: {
					'Content-Type': 'application/json',
					Accept: 'application/json, text/plain;q=0.9'
				},
				body: JSON.stringify(payload)
			});
			if (!res.ok) {
				const contentType = res.headers.get('content-type') || '';
				if (contentType.includes('application/json')) {
					const data = await res.json().catch(() => null);
					const message = data && (data.message || data.error || JSON.stringify(data));
					throw new Error(message || res.statusText || 'Request failed');
				}
				const raw = await res.text().catch(() => '');
				const friendly = stripHtml(raw).slice(0, 300);
				throw new Error(friendly || res.statusText || 'Request failed');
			}
			alert('User created. Now sign in.');
			setHidden(true);
		} catch (err) {
			const message = err?.message ? stripHtml(err.message) : 'Unable to create user.';
			showError(message);
			alert(`Create user failed: ${message}`);
		} finally {
			saveBtn.disabled = false;
			saveBtn.textContent = 'Create';
		}
	});
}
function initializeModalControls() {
	document.getElementById('modal-cancel').addEventListener('click', () => modalSetHidden(true));
	document.getElementById('modal-save').addEventListener('click', onSaveModal);
}

// === INITIAL RUN ===
document.addEventListener('DOMContentLoaded', () => {
	renderActionToolbars();
	initializeMenu();
	initializeAuthControls();
	initializeUserModal();
	initializeModalControls();
	document.getElementById('refresh').addEventListener('click', () => {
		const active = getActivePageKey();
		if (active === 'dashboard') {
			loadCounts();
		} else if (isAuthenticated()) {
			loadList(active);
		} else {
			alert('Please sign in to refresh data.');
		}
	});
	document.addEventListener('click', globalClickHandler);
	modalEl.addEventListener('click', (event) => {
		if (event.target === modalEl) {
			modalSetHidden(true);
		}
	});
	pages.dashboard.style.display = '';
	if (pages.list) {
		pages.list.style.display = 'none';
	}
	const dashboardBtn = document.querySelector('#menu button[data-page="dashboard"]');
	if (dashboardBtn) {
		dashboardBtn.classList.add('active');
	}
	setActiveUserDisplay();
	loadCounts();
});