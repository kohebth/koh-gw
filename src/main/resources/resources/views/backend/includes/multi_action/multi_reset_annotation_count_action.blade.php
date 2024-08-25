@can("edit_{$moduleName}")
    <button wire:click="updateMultiple('resetAnnotationCount')" class='btn btn-warning' type="button">
        <i class="fas fa-undo"></i> {{__("Reset annotation count")}}
    </button>
@endcan
