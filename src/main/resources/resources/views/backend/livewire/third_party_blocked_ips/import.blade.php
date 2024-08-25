<div class="row mt-4">
    <div class="col">
        <form wire:submit.prevent="save" class="form-horizontal content-loading">
            <x-loading/>
            {{ csrf_field()}}
            <div class="form-group row">
                <label class="col-sm-2 form-control-label" for="file">
                    Adblock file <span class="text-danger">(*.csv)</span>
                </label>
                <div class="col-sm-10">
                    <input type="file" class="form-control" wire:model="file" accept=".csv">
                    @error('file') <x-error-message>{{ $message }}</x-error-message> @enderror
                </div>
            </div>

            <div class="form-group row">
                {{ html()->label('Name <span class="text-danger">(*)</span>')->class('col-sm-2 form-control-label')->for('name') }}
                <div class="col-sm-10">
                    <select wire:model="name" class="form-control">
                        <option value=""></option>
                        @foreach($nameOptions as $name)
                            <option value="{{$name}}">{{__($name)}}</option>
                        @endforeach
                    </select>
                    @error('name')<x-error-message>{{$message}}</x-error-message> @enderror
                </div>
            </div>

            <div class="form-group row">
                @if(empty($this->file))
                    <button type="button" class="btn btn-secondary" wire:click="save" disabled>
                        <i class="fas fa-plus-circle"></i>
                        Upload
                    </button>
                @else
                    <button type="button" class="btn btn-primary" wire:click="save">
                        <i class="fas fa-plus-circle"></i>
                        Upload
                    </button>
                @endif

                <x-buttons.cancel> {{__('Cancel')}}</x-buttons.cancel>
            </div>
        </form>
    </div>
</div>

@push('after-styles')
    <style>
        .tooltip {
            position: relative;
            display: inline-block;
            border-bottom: 1px dotted black;
        }

        .tooltip .tooltiptext {
            visibility: hidden;
            width: 120px;
            background-color: black;
            color: #fff;
            text-align: center;
            border-radius: 6px;
            padding: 5px 0;

            /* Position the tooltip */
            position: absolute;
            z-index: 1;
        }

        .tooltip:hover .tooltiptext {
            visibility: visible;
        }
    </style>
@endpush


