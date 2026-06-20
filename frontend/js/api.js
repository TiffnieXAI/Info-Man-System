// API Configuration
const API_BASE_URL = "http://localhost:8080/api";

// sessionStorage helpers for the multi-step registration flow 
const REG_KEY = "philhealth_registration_draft";

function getRegistrationDraft() {
    const raw = sessionStorage.getItem(REG_KEY);
    return raw ? JSON.parse(raw) : { member: {}, contact: {}, dependents: [] };
}

function saveRegistrationDraft(draft) {
    sessionStorage.setItem(REG_KEY, JSON.stringify(draft));
}

function clearRegistrationDraft() {
    sessionStorage.removeItem(REG_KEY);
}

// API calls 
async function submitRegistration(member, contact) {
    const res = await fetch(`${API_BASE_URL}/registration`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ member, contact }),
    });
    const data = await res.json();
    if (!res.ok || !data.success) {
        throw new Error(data.message || "Registration failed.");
    }
    return data.data; // { member: {...}, contactInfo: {...} }
}

async function submitDependent(dependentPayload) {
    const res = await fetch(`${API_BASE_URL}/dependents`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(dependentPayload),
    });
    const data = await res.json();
    if (!res.ok || !data.success) {
        throw new Error(data.message || "Failed to save a dependent.");
    }
    return data.data;
}
